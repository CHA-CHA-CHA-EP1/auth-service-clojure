(ns chachacha.server
  (:require
   [ring.adapter.jetty :as jetty]
   [reitit.ring :as ring]
   [muuntaja.core :as m]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [chachacha.routes :as routes]))

(def app
  (ring/ring-handler
   (ring/router
    routes/routes
    {:data {:muuntaja m/instance
            :middleware [muuntaja/format-middleware]}})
   (ring/create-default-handler)))

(defonce server (atom nil))

(defn stop-server []
  (when @server
    (.stop @server)
    (reset! server nil)))

(defn start-server []
  (stop-server)
  (reset! server
          (jetty/run-jetty #'app {:port 3000 :join? false}))
  (println "Server running on http://localhost:3000")
  (println "Endpoints:")
  (println "  GET /health-check")
  (println "  POST /api/v1/auth/login"))

(defn -main [& _args]
  (start-server))
