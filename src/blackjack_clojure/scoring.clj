(ns blackjack-clojure.scoring)

(defn card-value
  ([card]
   (card-value card 0))
  ([card accumulative-score]
   (let [rank (:rank card)]
     (case rank
       (2 3 4 5 6 7 8 9 10) rank
       ("jack" "queen" "king") 10
       "ace" (if (> accumulative-score 10) 1 11)))))

(defn score-hand
  [hand]
  (let [sorted-hand (sort-by card-value hand)]
    (reduce #(+ % (card-value %2 %)) 0 sorted-hand)))

(defn bust?
  [hand]
  (< 21 (score-hand hand)))

(defn won?
  [player-hand dealer-hand]
  (let [player-score (score-hand player-hand)
        dealer-score (score-hand dealer-hand)]
    (and (not (bust? player-hand))
         (or (> player-score dealer-score)
             (bust? dealer-hand)))))
