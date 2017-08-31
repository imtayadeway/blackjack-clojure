(ns blackjack-clojure.core-test
  (:require [clojure.test :refer :all]
            [blackjack-clojure.core :refer :all]))

(deftest all-the-ranks
  (testing "That we have the full set of ranks in a standard deck."
    (is (= #{"ace" 2 3 4 5 6 7 8 9 10 "jack" "queen" "king"} ranks))))

(deftest all-the-suits
  (testing "That we have all the suits in a standard deck."
    (is (= #{"♠" "♥" "♦" "♣"} suits))))

(deftest deck-count
  (testing "That we have 52 cards in the deck."
    (is (= (count (deref deck)) 52))))


(deftest draw-player-hand
  (testing "That a hand can be represented as a string."
    (is (= "🂡 🂱"
           (draw-hand [{:rank "ace", :suit "♠"},
                       {:rank "ace", :suit "♥"}])))))

(deftest draw-dealers-hand
  (testing "That a dealer's hand can be represented as a string."
    (is (= "🂡 🂠"
           (draw-obscured-hand [{:rank "ace", :suit "♠"},
                                {:rank "ace", :suit "♥"}])))))

(deftest dealing
  (testing "That a hand can be dealt and returned to the pack."
    (let [hand (ref [])
          deck (ref [{:rank "ace", :suit "♠"},
                     {:rank 2, :suit "♠"},
                     {:rank 3, :suit "♠"}])]

      (deal hand deck)

      (is (= (deref hand) [{:rank "ace", :suit "♠"}
                           {:rank 2, :suit "♠"}]))
      (is (= (deref deck) [{:rank 3, :suit "♠"}]))

      (return-cards hand deck)

      (is (= (deref hand) []))
      (is (= (deref deck) [{:rank 3, :suit "♠"},
                           {:rank "ace", :suit "♠"},
                           {:rank 2, :suit "♠"}])))))
