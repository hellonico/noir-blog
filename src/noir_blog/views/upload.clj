(ns noir-blog.views.upload
  (:require [noir-blog.views.common :as common]
            [noir.response :as res])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers
        hiccup.form-helpers))

(defpage "/start" []
         (common/main-layout
           (form-to {:enctype "multipart/form-data"}
                    [:post "/upload"]
                    (label :file "File to upload")
                    (file-upload :file)
                    [:br]
                    (submit-button "Upload"))))

(defpage [:post "/upload"] {:keys [file]}
        (println file)
         (if-not (= "0" (:size file))
             (res/redirect "/success")
           (res/redirect "/fail")))

(defpage "/success" []
         (common/main-layout
           [:p "File upload successful!"]))

(defpage "/fail" []
         (common/main-layout
           [:p "File upload FAIL!"]))