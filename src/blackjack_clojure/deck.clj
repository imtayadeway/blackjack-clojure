(ns blackjack-clojure.deck)

(def suits #{"♠" "♥" "♦" "♣"})
(def ranks (set (concat ["ace"] (range 2 11) ["jack" "queen" "king"])))
(def deck (vec (for [suit suits
                     rank ranks]
                 {:suit suit, :rank rank})))

(defn create-deck
  [n]
  (->> (repeat n deck)
       (reduce concat)
       (shuffle)))

(defn deal
  [deck]
  [(subvec deck 4 (count deck)) (subvec deck 0 2) (subvec deck 2 4)])

(defn draw
  [deck hand]
  [(rest deck) (conj hand (first deck))])

(defn return-cards
  [deck player-hand dealer-hand]
  (vec (concat deck player-hand dealer-hand)))
