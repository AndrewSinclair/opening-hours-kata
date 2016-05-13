(ns open-hours.core-test
  (:require [clojure.test :refer :all]
            [open-hours.core :refer :all]
            [clj-time.core :refer :all]))

(def wednesday "2016-05-11T12:22:11.824Z")
(def thursday "2016-05-12T12:22:11.824Z")

(def ^:dynamic openingHours)

(defn default-fixture [f]
  (binding [openingHours (openhours.core.OpeningHours. "")]
    (f)))

(use-fixtures :once default-fixture)

(deftest config-hours
  (testing "The hours of operation config's hours range"
    (testing "it accepts a date in range")
      (is (.isOpenOn openingHours wednesday))
      (is (not (.isOpenOn openingHours thursday)))))

(deftest parse-date-time
  (testing "The datetime parsing"
    (testing "it can parse and unparse valid strings
               to dates and vice versa"
      (is (= wednesday
             (format-time (parse-time wednesday)))))))

