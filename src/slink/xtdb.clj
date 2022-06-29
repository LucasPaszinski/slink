(ns slink.db
  (:require [xtdb.api :as xt]))

(def node (xt/start-node {}))

(defn uuid [] (.toString (java.util.UUID/randomUUID)))

(uuid)

(def user-id1 (uuid))
(def user-id2 (uuid))

user-id1
user-id2

(def lucas {:xt/id user-id1
            :user/name "Lucas Lopes Paszinski"})

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

(xt/q (xt/db node)
      '{:find [e]
        :where [[e :link/url url]
                [e :link/user-id user-id]]
        :in [url user-id]}
      "www.google.com"
      "db61d59f-2355-409b-8e35-04b41744be4d")

;; To pull the whole document
(xt/q (xt/db node)
      '{:find [(pull e [*])]
        :where [[e :link/url url]
                [e :link/user-id user-id]]
        :in [url user-id]}
      "www.google.com"
      "db61d59f-2355-409b-8e35-04b41744be4d")

;; GET LINK WHERE SURL IS "1"

(xt/q (xt/db node) '{:find [(pull e [:link/url])]
                     :where [[e :link/surl surl]]
                     :in [surl]}
      "1")

;; JOIN?

(xt/q (xt/db node) '{:find [e1 e2]
                     :where [[e1 :xt/id id]
                             [e2 :link/user-id id]]})