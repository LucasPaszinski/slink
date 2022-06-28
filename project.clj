(defproject slink "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :repositories {"my.datomic.com" {:url "https://my.datomic.com/repo"
                                   :creds :gpg}}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [ring/ring-core "1.8.2"]
                 [ring/ring-jetty-adapter "1.8.2"]
                 [metosin/reitit "0.5.18"]
                 [metosin/muuntaja "0.6.8"]
                 [com.xtdb/xtdb-core "1.20.0"]
                 [com.xtdb/xtdb-rocksdb "1.20.0"]
                 [com.datomic/datomic-pro "0.9.6024"]]
  :repl-options {:init-ns slink.core})
