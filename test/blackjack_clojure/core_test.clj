(ns blackjack-clojure.core-test
  (:require [clojure.test :refer :all]
            [blackjack-clojure.core :refer :all]))

(deftest all-the-ranks
  (testing "That we have the full set of ranks in a standard deck."
    (is (= ranks [1 2 3 4 5 6 7 8 9 10 "jack" "queen" "king"]))))

(deftest all-the-suits
  (testing "That we have all the suits in a standard deck."
    (is (= suits #{"♠" "♥" "♦" "♣"}))))

(deftest the-full-deck
  (testing "That we have a full deck."
    (is (= (set deck)
           (set [{:rank 1, :suit "♣"}
                 {:rank 1, :suit "♦"}
                 {:rank 1, :suit "♥"}
                 {:rank 1, :suit "♠"}
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
