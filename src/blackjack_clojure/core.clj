(ns blackjack-clojure.core)
(require '[blackjack-clojure.scoring :as scoring])
(require '[blackjack-clojure.drawing :as drawing])
(require '[blackjack-clojure.deck :as deck])


(defn player-turn
  [deck player-hand dealer-hand]
  (do
    (drawing/draw-game player-hand dealer-hand)
    (println "Hit [h] or stand [s]?")
    (let [input (read-line)]
      (cond (= input "h")
            (let [[deck-after-draw player-hand-after-draw draw] (deck/draw deck player-hand)]
              (if (< 21 (scoring/score-hand player-hand-after-draw))
                (recur deck-after-draw player-hand-after-draw dealer-hand)
                [player-hand-after-draw deck-after-draw]))
            (= input "s")
            [player-hand deck]
            :else
            (recur deck player-hand dealer-hand)))))

(defn dealer-turn
  [deck player-hand dealer-hand]
  (do
    (drawing/draw-unobscured-game player-hand dealer-hand)
    (let [score (scoring/score-hand dealer-hand)]
      (cond (> score 17) [deck dealer-hand]
            :else (let [[deck-after-draw dealer-hand-after-draw] (deck/draw deck dealer-hand)]
                    (recur deck-after-draw player-hand dealer-hand-after-draw))))))

(defn play-round
  [deck]
  (let [[player-initial-hand dealer-initial-hand deck-after-deal] (deck/deal deck)
        [player-final-hand deck-after-player-turn] (player-turn player-initial-hand dealer-initial-hand deck-after-deal)
        [dealer-final-hand deck-after-dealer-turn] (dealer-turn player-final-hand dealer-initial-hand deck-after-player-turn)
        player-score (scoring/score-hand player-final-hand)
        dealer-score (scoring/score-hand dealer-final-hand)
        result (if (> player-score dealer-score) "won" "lost")]

    (println (str "You " result "!"))
    (Thread/sleep 2500)
    (deck/return-cards deck-after-dealer-turn player-final-hand dealer-final-hand)))

(defn -main
  [& args]
  (loop [deck (deck/create-deck 4)]
    (recur (play-round deck))))
