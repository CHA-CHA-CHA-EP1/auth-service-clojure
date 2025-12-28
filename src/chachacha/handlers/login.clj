(ns chachacha.handlers.login
  (:require [malli.core :as m]
            [malli.error :as me]))

(def login-schema
  [:map
   [:email [:re {:error/message "Invalid email format"}
            #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"]]
   [:password [:string {:min 8 :error/message "Password must be at least 8 characters"}]]])

(defn validate-login-request
  [data]
  (if (m/validate login-schema data)
    {:valid? true :data data}
    {:valid? false
     :errors (-> login-schema
                 (m/explain data)
                 (me/humanize))}))

(defn login-handler
  [request]
  (let [body (:body-params request)
        validation (validate-login-request body)]
    (if (:valid? validation)
      (let [{:keys [email password]} (:data validation)]
        (println "email:" email "password:" password)
        {:status 200
         :body {:message "Login received"}})
      {:status 400
       :body {:errors (:errors validation)}})))
