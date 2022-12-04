(ns advent-of-code-2022-clojure.day04
  (:require [clojure.string :refer [split-lines split]]
            [clojure.set :refer [subset? intersection]]))

(defn generate-range [s]
  (let [[num1 num2 num3 num4] (map #(Integer/parseInt %) (split s #",|-"))]
    [(set (range num1 (inc num2)))
     (set (range num3 (inc num4)))]))

(defn fully-contains? [[set1 set2]]
  (or (subset? set1 set2) (subset? set2 set1)))

(defn overlaps? [[set1 set2]]
  (> (count (intersection set1 set2)) 0))

(def ranges
  (->> (slurp "resources/day04.txt")
       split-lines
       (map generate-range)))

(time (->> ranges
           (filter fully-contains?)
           count))
;433

(time (->> ranges
           (filter overlaps?)
           count))
;852
