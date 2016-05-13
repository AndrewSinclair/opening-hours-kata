(ns open-hours.core
  (:require [clj-time.core :as t]
            [clj-time.format :as f]
            [clj-time.predicates :as pr])
  (:gen-class
    :name openhours.core.OpeningHours
    :init init
    :state state
    :constructors {[Array String String] []}
    :methods [[isOpenOn [String] Boolean]]))

(defn format-time
  [timestamp]
  (let [basic-formatter (f/formatters :date-time)]
    (f/unparse basic-formatter timestamp)))

(defn parse-time
  [timestamp]
  (let [basic-formatter (f/formatters :date-time)]
    (f/parse basic-formatter timestamp)))

(defn -init
  [shop-config]
  [[] shop-config])

(defn -isOpenOn
  "parse the passed in date:
    check: if it's on one of the days in the config 
           and it's in the time range"
  [this datetime-string]
  (let [state     (.state this)
        datetime  (parse-time datetime-string)
        days      (:days state)
        hours     (:hours state)
        right-day (or (and (contains? :monday (days)) (pr/monday? datetime))
                      (and (contains? :tuesday (days)) (pr/tuesday? datetime))
                      (and (contains? :wednesday (days)) (pr/wednesday? datetime))
                      (and (contains? :thursday (days)) (pr/thursday? datetime))
                      (and (contains? :friday (days)) (pr/monday? datetime))
                      (and (contains? :saturday (days)) (pr/monday? datetime))
                      (and (contains? :sunday (days)) (pr/monday? datetime)))]
    (= (parse-time datetime) )))

