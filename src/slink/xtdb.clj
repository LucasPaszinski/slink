(ns slink.db
  (:require [xtdb.api :as xt]))

(def node (xt/start-node {}))

(defn uuid [] (.toString (java.util.UUID/randomUUID)))

(uuid)

(def user-id1 (uuid))
(def user-id2 (uuid))

(def lucas {:xt/id user-id1
            :user/name "Lucas Lopes Paszinski"})

lucas

(def naty   {:xt/id user-id2
             :user/name "Natalia Cardoso Flores"})

(def lucas-link {:xt/id (uuid)
                 :link/url "www.google.com"
                 :link/surl "0"
                 :link/user-id user-id1})

(def naty-link  {:xt/id (uuid)
                 :link/url "www.google.com"
                 :link/surl "1"
                 :link/user-id user-id2})


(xt/submit-tx node [[::xt/put lucas]
                    [::xt/put naty]
                    [::xt/put lucas-link]
                    [::xt/put naty-link]])


;; GET LINK BY URL AND USER_ID
(->
 node
 (xt/db)
 (xt/q
  '{:find [e]
    :where [[e :link/url url]
            [e :link/user-id user-id]]
    :in [url user-id]}
  "www.google.com"
  "260d0208-31ec-43f6-baad-78c2be54f348"))

;; GET LINK WHERE SURL IS "1"
(-> node
    (xt/db)
    (xt/q '{:find [e]
            :where [[e :link/surl surl]]
            :in [surl]}
          "1"))

;; JOIN?
(-> node
    (xt/db)
    (xt/q '{:find [e1 e2]
            :where [[e1 :xt/id id]
                    [e2 :link/user-id id]]}))

