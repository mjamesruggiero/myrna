(ns myrna.core
  (:require [libpython-clj.require :refer [require-python]]
            [libpython-clj.python :as py :refer [py. py.. py.-]]))

(require-python '[spacy :as spacy])

(def nlp (spacy/load "en_core_web_sm"))

(def sample-text
  "Real-time sentiment analysis can even put you one step ahead of a potential PR crisis, allowing you to take action before a customer’s bad experience goes viral.")

(map (fn [token]
       [(py.- token text) (py.- token pos_) (py.- token dep_)])
     (nlp sample-text))

(let [doc (nlp "Apple is looking at buying U.K. startup for $1 billion")]
  (map (fn [token]
         [(py.- token text) (py.- token pos_) (py.- token dep_)])
       doc))

(let [doc (nlp "Apple is looking at buying U.K. startup for $1 billion")]
  (map (fn [ent]
         {:text (py.- ent text)
          :start-char (py.- ent start_char)
          :end-char (py.- ent end_char)
          :label (py.- ent label_)} )
       (py.- doc ents)))
