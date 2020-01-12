/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

db.createUser(
    {
      user: "introuser",
      pwd: "intropass",
      roles: [{role: "readWrite", db: "introdb"},
        {role: "read", db: "reporting"}]
    }
);