(ns cloj-mopidy.core
   (:require [clj-http.client :as client]
             [clojure.data.json :as json]
             [clojure.walk :as w]))

(def mopidy-host "localhost")
(def mopidy-port 6680)

(def mopidy-url (str "http://" mopidy-host ":" mopidy-port "/mopidy/rpc"))

;; (def get-state-method "core.playback.get_state") 

(defn parse-rpc-response-body [resp]
  (w/keywordize-keys (get (json/read-str (:body resp)) "result")))

(defn rpc-request [method]
  (client/post mopidy-url {:body (json/write-str {:jsonrpc "2.0" :id 1 :method method})}))

(defn mop-rpc [method]
  (parse-rpc-response-body (rpc-request method)))

