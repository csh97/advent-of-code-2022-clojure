(ns advent-of-code-2022-clojure.day01)


(let [calories (->> (slurp "resources/day01.txt")
                    (clojure.string/split-lines)
                    (partition-by #(= % ""))
                    (remove #(= % [""]))
                    (map #(map read-string %))
                    (map #(apply + %)))]
  (time (apply max calories))

  (time (->> calories
             sort
             (take-last 3)
             (apply +))))




