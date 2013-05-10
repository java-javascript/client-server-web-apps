(require '[clojure.data.json :as json])

(println "*** JSON Clojure ***")

(def recs (json/read-str (slurp "data/test.json")))

(doseq [x recs] (println (x "title")))