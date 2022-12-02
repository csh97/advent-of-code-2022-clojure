(ns advent-of-code-2022-clojure.day02
  (:require [clojure.string :refer [split-lines split]]))

(def moves
  {"A" {:winning-move "Y" :points 1 :winning-move-actual "B" :matching "X"}
   "B" {:winning-move "Z" :points 2 :winning-move-actual "C" :matching "Y"}
   "C" {:winning-move "X" :points 3 :winning-move-actual "A" :matching "Z"}})

(defn get-losing-move [opponent-move]
  (first (remove #(or (= % opponent-move) (= % (:winning-move-actual (moves opponent-move)))) ["A" "B" "C"])))

(defn calc-score [win-score move-score score [opponent me] ]
  (+ score (win-score me opponent) (move-score me opponent)))

(defn win-score-part-1 [me opponent]
  (cond
    (= (:matching (moves opponent)) me) 3
    (= (:winning-move (moves opponent)) me) 6
    :else 0))

(defn win-score-part-2 [me _]
  (case me
    "X" 0
    "Y" 3
    "Z" 6))

(defn move-score-part-1 [me _]
  (case me
    "X" 1
    "Y" 2
    "Z" 3))

(defn move-score-part-2 [me opponent]
  (case me
    "X" (:points (moves (get-losing-move opponent)))
    "Y" (:points (moves opponent))
    "Z" (:points (moves (:winning-move-actual (moves opponent))))))

(def rounds
  (->> (slurp "resources/day02.txt")
       split-lines
       (map #(split % #" "))))

(time (reduce (partial calc-score win-score-part-1 move-score-part-1) 0 rounds));11386
(time (reduce (partial calc-score win-score-part-2 move-score-part-2) 0 rounds));13600
