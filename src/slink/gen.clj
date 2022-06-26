(ns slink.gen
  (:require [clojure.string :as str]))

(def base 62)
(def to-base62 (str/split "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" #""))

(def slink-count (ref 0))

(defn encode [n]
  (loop [n n
         a ""]
    (let [div (quot n base)
          mod (rem n base)]
      (if (zero? div)
        (str (to-base62 mod) a)
        (recur div (str (to-base62  mod) a))))))

(defn next-slink []
  (let [surl (encode @slink-count)]
    (dosync (alter slink-count inc))
    surl))