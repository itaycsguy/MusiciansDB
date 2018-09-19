package app.itaycsguy.musiciansdb


class User(details : HashMap<String,String>) {
    private val _details : HashMap<String,String> = details.clone() as HashMap<String,String>

    public fun getDetialsMap() : HashMap<String,String> {
        return this._details
    }

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

    public fun setDisplayName(name : String) {
        this._details.put("user_name",name)
    }

    public fun setEmail(email : String) {
        this._details.put("email",email)
    }

    public fun setPhoto(photo : String) {
        this._details.put("photo",photo)
    }

    public fun setGivenName(name : String) {
        this._details.put("given_name",name)
    }

    public fun setFamilyName(name : String) {
        this._details.put("family_name",name)
    }
}