(ns slink.slinks
  (:require [slink.gen :as gen]
            [ring.util.response :as resp]))

(def slinks (atom {}))

(defn add-slink [slink]
  (swap! slinks assoc (str (slink :user_id) (slink :surl)) slink))

(defn create-slink [req]
  (let [surl (assoc (req :body-params) :surl (gen/next-slink))]
    (add-slink surl)
    {:status 200 :body surl}))

(defn get-url [req]
  (let [surl (-> req :path-params :surl)
        url ((@slinks surl) :url)]
    (resp/redirect url)))