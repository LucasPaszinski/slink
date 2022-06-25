(ns slink.slinks
  (:require [slink.gen :as gen]
            [clojure.pprint :as pp]
            [ring.util.response :as resp]))

(def slinks (atom {}))

((@slinks "d") :url)

(defn add-slink [slink]
  (swap! slinks assoc (str (slink :user_id) (slink :surl)) slink))

(defn create-slink [req]
  (pp/pprint req)
  (let [surl (assoc (req :body-params) :surl (gen/next-slink))]
    (add-slink surl)
    {:status 200 :body surl}))

(defn get-url [req]
  (let [surl (-> req :path-params :surl)
        url ((@slinks surl) :url)]
    (pp/pprint url)
    (resp/redirect url)))