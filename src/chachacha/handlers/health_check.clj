(ns chachacha.handlers.health-check)

(defn health-check
  [_request]
  {:status 200
   :body {:message "I'm alive"}})
