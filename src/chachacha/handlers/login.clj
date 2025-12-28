(ns chachacha.handlers.login
  (:require [malli.core :as m]
            [malli.error :as me]))

(def LoginSchema
  [:map
   [:email [:string {:min 3}]]
   [:password [:string {:min 8}]]])

(defn login-handler
  [request]
  (let [body (:body-params request)]

    (println "request body:" body)

    (if (m/validate LoginSchema body)
      (let [email (:email body)
            password (:password body)]
        (println "email " email "password: " password)

        {:status 200
         :body {:message "Login received"}})
      {:status 400
       :body {:message "Invalid request"}})))

