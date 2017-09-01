(ns blackjack-clojure.core)
(require '[blackjack-clojure.scoring :as scoring])
(require '[blackjack-clojure.drawing :as drawing])
(require '[blackjack-clojure.deck :as deck])

(defn deal-card
  [hand deck]
  (dosync
   (ref-set hand (conj @hand (first @deck)))
   (ref-set deck (rest @deck))))

(defn deal
  [hand deck]
  (dotimes [n 2] (deal-card hand deck)))

(defn return-cards
  [hand deck]
  (dosync
   (ref-set deck (vec (concat @deck @hand)))
   (ref-set hand [])))

(defn play-hand
  []
  (do
    (println "Hit [h] or stand [s]?")
    (let [input (read-line)]
      (cond (= "h" input)
            (do
              (deal-card player-hand deck)
              (cond (scoring/bust? player-hand) "do something"
                    (scoring/blackjack? player-hand) "you won!"
                    :else (play-hand)))))
    (println (draw-hand (deref player-hand)))))

(defn play-round
  []
  (do
    (shuffle-deck deck)
    (deal player-hand deck)
    (deal dealer-hand deck)
    (draw-game)
    (play-hand)
    (println "This is your score:" (score-hand player-hand))
    (return-cards player-hand deck)
    (return-cards dealer-hand deck)))

(defn -main
  [& args])
