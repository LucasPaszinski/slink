(ns slink.datomic.db
  (:require [datomic.api :as d]))

(def uri "datomic:dev://localhost:4334/hello-datomic")

(def link-schema
  [{:db/ident :link/url
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}

   {:db/ident :link/surl
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}

   {:db/ident :link/user-id
    :db/valueType :db.type/instant
    :db/cardinality :db.cardinality/one}])

(def user-schema
  [{:db/ident :user/id
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}

   {:db/ident :user/name
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}])

(defn setup-db []
  (let [succ (d/create-database uri)]
    (if succ
      (let [conn (d/connect uri)
            db (d/db conn)]
        (let [resp (d/transact conn link-schema)
              resp (d/transact conn user-schema)]
          (println "schema creation resp" resp))))))


(defn delete-db []
  (let [succ (d/delete-database uri)]
    (println "deletion" succ)))

(def data
  (let [[id1 id2] [(uuid) (uuid)]]
    [{:user/id id1
      :user/name "Lucas Paszinski"}

     {:link/url "www.google.com"
      :link/surl "0"
      :link/user-id id1}

     {:link/url "www.monkeytype.com"
      :link/surl "1"
      :link/user-id id1}
     
     {:user/id id2
      :user/name "Natalia Flores"}

     {:link/url "www.google.com"
      :link/surl "2"
      :link/user-id id2}

     {:link/url "www.monkeytype.com"
      :link/surl "3"
      :link/user-id id2}]))

(defn fillup-db []
  (let [conn (d/connect uri)
        db (d/db conn)
        resp (d/transact conn data)]
    (println "post insertion resp" resp)))

(setup-db)
(fillup-db)

(def all-posts-q
  '[:find ?e]
  :where [?e :post/title])

(defn get-all-posts []
  (let [conn (d/connect uri)
        db (d/db conn)
        posts (d/q all-posts-q db)]
    posts))