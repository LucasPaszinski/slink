(ns slink.web)

(use 'ring.adapter.jetty)

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World"})

(run-jetty handler {:port 3000
                    :join? false})