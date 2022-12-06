(ns advent-of-code-2022-clojure.day05
  (:require [clojure.string :as str]))

(def stacks
  {1 ["Z" "J" "N" "W" "P" "S"]
   2 ["G" "S" "T"]
   3 ["V" "Q" "R" "L" "H"]
   4 ["V" "S" "T" "D"]
   5 ["Q" "Z" "T" "D" "B" "M" "J"]
   6 ["M" "W" "T" "J" "D" "C" "Z" "L"]
   7 ["L" "P" "M" "W" "G" "T" "J"]
   8 ["N" "G" "M" "T" "B" "F" "Q" "H"]
   9 ["R" "D" "G" "C" "P" "B" "Q" "W"]})

(defn remove-last [v]
  (into [] (take (dec (count v)) v)))

(defn remove-last-n [v n]
  (into [] (take (- (count v) n) v)))

(defn add-to-end [v i]
  (conj v i))

(defn add-all-to-end [v i]
  (apply (partial conj v) i))

(defn move-box [[from to] stacks _]
  (let [from-stack (get stacks from)
        last (peek from-stack)]
      (-> stacks
          (update from remove-last)
          (update to #(add-to-end % last)))))

(defn move-boxes [stacks [count from to]]
  (reduce (partial move-box [(Integer/parseInt from) (Integer/parseInt to)]) stacks (range 1 (inc (Integer/parseInt count)))))

(defn move-boxes-v2 [stacks [c from to]]
  (let [from (Integer/parseInt from)
        to (Integer/parseInt to)
        c (Integer/parseInt c)
        from-stack (get stacks from)
        to-move (subvec from-stack (- (count from-stack) c))]
    (-> stacks
        (update from #(remove-last-n % c))
        (update to #(add-all-to-end % to-move)))))

(defn remove-stacks [s]
  (subvec s 10))

(defn result-to-string [result]
  (str/join (map #(last (get result %)) (range 1 10))))

(def instructions
  (->> (slurp "resources/day05.txt")
       str/split-lines
       remove-stacks
       (map #(str/split % #"move | from | to "))
       (map #(remove (fn [s] (= s "")) %))
       (map #(into [] %))))

(->> instructions
     (reduce move-boxes stacks)
     result-to-string
     (str/join))

(->> instructions
     (reduce move-boxes-v2 stacks)
     result-to-string
     (str/join))

;MQTPGLLDN

;LVZPSTTCZ