(ns open-hours.core-test
  (:require [clojure.test :refer :all]
            [open-hours.core :refer :all]
            [clj-time.core :refer :all]))

(def wednesday "2016-05-11T12:22:11.824Z")
(def wednesday-morning "2016-05-11T06:22:11.824Z")
(def thursday "2016-05-12T12:22:11.824Z")
(def friday "2016-05-13T12:22:11.824Z")

(def sample-config 
  {:days [:monday :wednesday :friday] :hours ["8:00" "10:00"]})

(def ^:dynamic openingHours)

(defn setup-shop-fixture
  [shop-config]
  (defn default-fixture [f]
    (binding [openingHours (new openhours.core.OpeningHours shop-config)]
      (f)))
  (use-fixtures :once default-fixture))


(deftest opening-hours-constructor
  (testing "The OpeningHours instance"
    (testing "takes a timestamp and sets the object with it"
      (is (instance? openhours.core.OpeningHours
                     (new openhours.core.OpeningHours wednesday)))
      (is (thrown? java.lang.IllegalArgumentException
                   (new openhours.core.OpeningHours "invalid as a timestamp"))))))


(setup-shop-fixture sample-config)

(deftest config-hours
  (testing "The ShopConfig's hour range checker"
    (testing "accepts a date in range"
      (is (.isOpenOn openingHours wednesday))
      (is (not (.isOpenOn openingHours thursday))
      (is (not (.isOpenOn openingHours wednesday-morning))
      (is (.isOpenOn openingHours friday)))))))

(deftest parse-date-time
  (testing "The datetime parsing"
    (testing "can parse and unparse valid strings
               to dates and vice versa"
      (is (= wednesday
             (format-time (parse-time wednesday)))))))

