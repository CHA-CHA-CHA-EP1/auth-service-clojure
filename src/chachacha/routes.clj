(ns chachacha.routes
  (:require [chachacha.handlers.login :as login]
            [chachacha.handlers.health-check :as health-check]))

(def routes
  [["/health-check" {:get {:handler health-check/health-check}}]
   ["/api/v1/auth/login" {:post {:handler login/login-handler}}]])
