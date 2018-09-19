package app.itaycsguy.musiciansdb


class User(details : HashMap<String,String>) {
    private val _details : HashMap<String,String> = details.clone() as HashMap<String,String>

    public fun getUserName() : String {
        return this._details.get("user_name").toString()
    }

    public fun getEmail() : String {
        return this._details.get("email").toString()
    }

    public fun getPhoto() : String {
        return this._details.get("photo").toString()
    }

    public fun getGivenName() : String {
        return this._details.get("given_name").toString()
    }

    public fun getFamilyName() : String {
        return this._details.get("family_name").toString()
    }
}