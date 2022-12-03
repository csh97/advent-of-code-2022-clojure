(ns advent-of-code-2022-clojure.day03
  (:require [clojure.string :refer [split-lines split]]
            [clojure.set :refer [intersection]]
            [clojure.string :as str]))

(defn lower-case-to-num [letter]
  (- (int letter) 96))

(defn upper-case-to-num [letter]
  (- (int letter) 38))

(defn calc-priority [priority letter]
  (if (Character/isUpperCase letter)
    (+ priority (upper-case-to-num letter))
    (+ priority (lower-case-to-num letter))))

(defn partition-in-half [l]
  (partition (/ (count l) 2) l))

(def input-split
  (->> (slurp "resources/day03.txt")
       (split-lines)))

(defn find-total-priority [bags]
  (->> bags
       (mapcat #(apply intersection (map set %)))
       (reduce calc-priority 0)))

(time (->> input-split
           (map partition-in-half)
           find-total-priority))
;7716

(time (->> input-split
           (partition 3)
           find-total-priority))
;2973
