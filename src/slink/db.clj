(ns slink.db
  (:require [xtdb.api :as xt]))

(def node (xt/start-node {}))

(defn uuid [] (.toString (java.util.UUID/randomUUID)))

(defn user-xt-id []
  (keyword "user/id" (uuid)))

(defn slink-xt-id [surl]
  (keyword "slinks/id" (uuid)))

(defn create-and-sync-entity [entity]
  (xt/submit-tx node [[::xt/put entity]])
  (xt/sync node)
  entity)

(defn create-user [name]
  (create-and-sync-entity {:xt/id (user-xt-id)
                           :name name}))

(defn create-short-link [{:keys [surl url user_id]}]
  (create-and-sync-entity {:xt/id (slink-xt-id surl)
                           :id/user user_id
                           :url url
                           :surl surl}))

(let [user (create-user "Lucas Lopes Paszinski")
      slink (create-short-link
             {:surl "0"
              :url "http://www.google.com"
              :id/user (user :xt/id)})]
  slink)

(create-short-link
 {:surl "dsaf23d"
  :url "https://xtdb.com"
  :user_id (uuid)})

(defn get-short-link [surl]
  (->>
   (xt/db node)
   (xt/q '{:find [p] :where [[p :name]]})
   (xt/entity (xt/db node) (short-link-xt-id surl)))
  
(defn by-surl [surl]
   (->
   (xt/db node)
   (xt/q '{:find [p] :where [[p :name]]})))
  
(by-surl "dsaf23d")
