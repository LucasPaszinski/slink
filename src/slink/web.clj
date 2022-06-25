(ns slink.web
  (:require [ring.adapter.jetty :as jetty]
            [reitit.ring :as rr]
            [muuntaja.core :as m]
            [slink.slinks :as slinks]
            [ring.middleware.params :as params]
            [reitit.ring.middleware.muuntaja :as muuntaja])
  (:gen-class))


(def app
  (rr/ring-handler
   (rr/router
    [["/:surl" slinks/get-url]
     ["/links/create" slinks/create-slink]]
    {:data {:muuntaja   m/instance
            :middleware [params/wrap-params
                         muuntaja/format-middleware]}})))


(defn start []
  (jetty/run-jetty #'app {:port 3000 :join? false}))

(start)