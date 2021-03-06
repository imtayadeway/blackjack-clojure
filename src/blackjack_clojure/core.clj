(ns blackjack-clojure.core
  (:gen-class))
(require '[blackjack-clojure.scoring :as scoring])
(require '[blackjack-clojure.drawing :as drawing])
(require '[blackjack-clojure.deck :as deck])


(defn clear-screen
  []
  (print (str (char 27) "[2J")))

(defn player-turn
  [deck player-hand dealer-hand]
  (do
    (clear-screen)
    (println (drawing/draw-game player-hand dealer-hand))
    (println "Hit [h] or stand [s]?")
    (let [input (read-line)]
      (case input
        "h" (let [[deck-after-draw player-hand-after-draw draw] (deck/draw deck player-hand)]
              (if (scoring/bust? player-hand-after-draw)
                [deck-after-draw player-hand-after-draw]
                (recur deck-after-draw player-hand-after-draw dealer-hand)))
        "s" [deck player-hand]
        (recur deck player-hand dealer-hand)))))

(defn dealer-turn
  [deck player-hand dealer-hand]
  (do
    (clear-screen)
    (println (drawing/draw-unobscured-game player-hand dealer-hand))
    (let [score (scoring/score-hand dealer-hand)]
      (cond (or (scoring/bust? player-hand) (> score 17)) [deck dealer-hand]
            :else (let [[deck-after-draw dealer-hand-after-draw] (deck/draw deck dealer-hand)]
                    (Thread/sleep 1500)
                    (recur deck-after-draw player-hand dealer-hand-after-draw))))))

(defn result
  [player-hand dealer-hand]
  (if (scoring/won? player-hand dealer-hand) "won" "lost"))

(defn play-round
  [deck]
  (let [[deck-after-player-deal
         player-initial-hand] (deck/deal deck)
        [deck-after-deal
         dealer-initial-hand] (deck/deal deck-after-player-deal)
        [deck-after-player-turn
         player-final-hand] (player-turn deck-after-deal player-initial-hand dealer-initial-hand)
        [deck-after-dealer-turn
         dealer-final-hand] (dealer-turn deck-after-player-turn player-final-hand dealer-initial-hand)]

    (println (str "You " (result player-final-hand dealer-final-hand) "!"))
    (Thread/sleep 2500)
    (deck/return-cards deck-after-dealer-turn (into player-final-hand dealer-final-hand))))

(defn -main
  [& args]
  (loop [deck (deck/create-deck 4)]
    (recur (play-round deck))))
