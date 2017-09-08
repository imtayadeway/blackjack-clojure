(ns blackjack-clojure.deck-test
  (:require [clojure.test :refer :all]
            [blackjack-clojure.deck :refer :all]))

(deftest all-the-ranks
  (testing "That we have the full set of ranks in a standard deck."
    (is (= #{"ace" 2 3 4 5 6 7 8 9 10 "jack" "queen" "king"} ranks))))

(deftest all-the-suits
  (testing "That we have all the suits in a standard deck."
    (is (= #{"♠" "♥" "♦" "♣"} suits))))

(deftest single-deck-count
  (testing "That we have 52 cards in a single deck."
    (is (= (count (create-deck 1)) 52))))

(deftest two-deck-count
  (testing "That we have 104 cards with two decks."
    (is (= (count (create-deck 2)) 104))))

(deftest four-deck-count
  (testing "That we have 208 cards with four decks."
    (is (= (count (create-deck 4)) 208))))

(deftest dealing
  (testing "That two cards are dealt from the deck"
    (let [deck (conj clojure.lang.PersistentQueue/EMPTY
                     {:rank "ace", :suit "♠"}
                     {:rank 2, :suit "♠"}
                     {:rank 3, :suit "♠"}
                     {:rank 4, :suit "♠"})
          expected [[{:rank 3, :suit "♠"} {:rank 4, :suit "♠"}]
                    [{:rank "ace", :suit "♠"} {:rank 2, :suit "♠"}]]
          actual (deal deck)]
      (is (= expected actual))
      (is (= clojure.lang.PersistentQueue (class (first actual)))))))

(deftest returning
  (testing "That the cards are returned to the pack in the correct order"
    (let [cards [{:rank "ace", :suit "♠"} {:rank 2, :suit "♠"}]
          deck (conj clojure.lang.PersistentQueue/EMPTY
                     {:rank 3, :suit "♠"}, {:rank 4, :suit "♠"})
          expected [{:rank 3, :suit "♠"}
                    {:rank 4, :suit "♠"}
                    {:rank "ace", :suit "♠"}
                    {:rank 2, :suit "♠"}]
          actual (return-cards deck cards)]
      (is (= expected actual))
      (is (= clojure.lang.PersistentQueue (class actual))))))

(deftest drawing
  (testing "That a card can be drawn"
    (let [deck (conj clojure.lang.PersistentQueue/EMPTY
                     {:rank 2, :suit "♠"} {:rank 3, :suit "♠"})
          hand [{:rank "ace", :suit "♠"}]
          expected [[{:rank 3, :suit "♠"}]
                    [{:rank "ace", :suit "♠"} {:rank 2, :suit "♠"}]]
          actual (draw deck hand)]
      (is (= expected actual))
      (is (= clojure.lang.PersistentQueue (class (first actual)))))))
