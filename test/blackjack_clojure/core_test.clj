(ns blackjack-clojure.core-test
  (:require [clojure.test :refer :all]
            [blackjack-clojure.core :refer :all]))

(deftest all-the-ranks
  (testing "That we have the full set of ranks in a standard deck."
    (is (= ranks #{"ace" 2 3 4 5 6 7 8 9 10 "jack" "queen" "king"}))))

(deftest all-the-suits
  (testing "That we have all the suits in a standard deck."
    (is (= suits #{"♠" "♥" "♦" "♣"}))))

(deftest the-full-deck
  (testing "That we have a full deck."
    (is (= (set deck)
           (set [{:rank "ace", :suit "♣"}
                 {:rank "ace", :suit "♦"}
                 {:rank "ace", :suit "♥"}
                 {:rank "ace", :suit "♠"}
                 {:rank 2, :suit "♣"}
                 {:rank 2, :suit "♦"}
                 {:rank 2, :suit "♥"}
                 {:rank 2, :suit "♠"}
                 {:rank 3, :suit "♣"}
                 {:rank 3, :suit "♦"}
                 {:rank 3, :suit "♥"}
                 {:rank 3, :suit "♠"}
                 {:rank 4, :suit "♣"}
                 {:rank 4, :suit "♦"}
                 {:rank 4, :suit "♥"}
                 {:rank 4, :suit "♠"}
                 {:rank 5, :suit "♣"}
                 {:rank 5, :suit "♦"}
                 {:rank 5, :suit "♥"}
                 {:rank 5, :suit "♠"}
                 {:rank 6, :suit "♣"}
                 {:rank 6, :suit "♦"}
                 {:rank 6, :suit "♥"}
                 {:rank 6, :suit "♠"}
                 {:rank 7, :suit "♣"}
                 {:rank 7, :suit "♦"}
                 {:rank 7, :suit "♥"}
                 {:rank 7, :suit "♠"}
                 {:rank 8, :suit "♣"}
                 {:rank 8, :suit "♦"}
                 {:rank 8, :suit "♥"}
                 {:rank 8, :suit "♠"}
                 {:rank 9, :suit "♣"}
                 {:rank 9, :suit "♦"}
                 {:rank 9, :suit "♥"}
                 {:rank 9, :suit "♠"}
                 {:rank 10, :suit "♣"}
                 {:rank 10, :suit "♦"}
                 {:rank 10, :suit "♥"}
                 {:rank 10, :suit "♠"}
                 {:rank "jack", :suit "♣"}
                 {:rank "jack", :suit "♦"}
                 {:rank "jack", :suit "♥"}
                 {:rank "jack", :suit "♠"}
                 {:rank "queen", :suit "♣"}
                 {:rank "queen", :suit "♦"}
                 {:rank "queen", :suit "♥"}
                 {:rank "queen", :suit "♠"}
                 {:rank "king", :suit "♣"}
                 {:rank "king", :suit "♦"}
                 {:rank "king", :suit "♥"}
                 {:rank "king", :suit "♠"}
                 ])))))

(deftest deck-count
  (testing "That we have 52 cards in the deck."
    (is (= (count deck) 52))))

(deftest score-two-twos
  (testing "That we can score a hand with two twos."
    (is (= (score-hand [{:rank 2 :suit "♥"} {:rank 2 :suit "♣"}])
           4))))

(deftest score-two-three
  (testing "That we can score a hand with a two and a three."
    (is (= (score-hand [{:rank 2 :suit "♥"} {:rank 3 :suit "♣"}])
           5))))
(deftest score-two-three-four
  (testing "That we can score a hand with three cards."
    (is (= (score-hand [{:rank 2 :suit "♥"} {:rank 3 :suit "♣"} {:rank 4 :suit "♦"}])
           9))))

(deftest score-with-face-card
  (testing "That we can score a hand with a face card in it."
    (is (= (score-hand [{:rank "jack" :suit "♥"} {:rank 2 :suit "♣"}])
           12))))

(deftest score-ace-two
  (testing "That we can score a hand with a high ace."
    (is (= (score-hand [{:rank "ace" :suit "♥"} {:rank 2 :suit "♣"}])
           13))))

(deftest score-ace-10
  (testing "That we can score a hand with a low ace.")
  (is (= (score-hand [{:rank "ace" :suit "♥"} {:rank 9 :suit "♣"} {:rank 2 :suit "♦"}])
         12)))

(deftest score-10-ace
  (testing "That we can score a hand with a low ace irrespective of order."))

(deftest score-ace-ace)
