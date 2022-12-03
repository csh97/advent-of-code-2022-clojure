(ns advent-of-code-2022-clojure.day03
  (:require [clojure.string :refer [split-lines split]]
            [clojure.set :refer [intersection]]
            [clojure.string :as str]))

(defn lower-case-to-num [letter]
  (- (int (last letter)) 96))

(defn upper-case-to-num [letter]
  (- (int (last letter)) 38))

(defn uppercase? [letter]
  (= letter (str/upper-case letter)))

(defn calc-priority [priority letter]
  (if (uppercase? letter)
    (+ priority (upper-case-to-num letter))
    (+ priority (lower-case-to-num letter))))

(defn partition-in-half [l]
  (partition (/ (count l) 2) l))

(def input-split
  (->> (slurp "resources/day03.txt")
       (split-lines)
       (map #(split % #""))))

(defn find-total-priority [bag-sets]
  (->> bag-sets
       (mapcat #(apply intersection %))
       (reduce calc-priority 0)))

(time (->> input-split
           (map partition-in-half)
           (map #(map set %))
           find-total-priority))
;7716

(time (->> input-split
           (map set)
           (partition 3)
           find-total-priority))
;2973
