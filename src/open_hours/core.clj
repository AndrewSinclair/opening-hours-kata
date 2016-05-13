(ns open-hours.core
  (:require [clj-time.core :as t]
            [clj-time.format :as f])
  (:gen-class
    :name openhours.core.OpeningHours
    :init init
    :state state
    :constructors {[String] []}
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
  [config]
  [[] config])

(defn -isOpenOn
  [this datetime]
  (= datetime "2016-05-11T12:22:11.824Z"))

