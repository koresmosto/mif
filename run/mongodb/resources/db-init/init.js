db.auth('root', 'rootpass');

db.createUser(
    {
      user: "introuser",
      pwd: "intropass",
      roles: [{role: "readWrite", db: "introdb"},
        {role: "read", db: "reporting"}]
    }
);

db = db.getSiblingDB('introdb');

db.createCollection("info");
db.info.createIndex({"key": 1}, {"unique": true})
db.info.insert(
    {"key": "author", "details": "koresmosto@gmail.com (container docker)"});
db.info.insert({
  "key": "purpose",
  "details": "Social Network for workers (container docker)"
});
db.info.insert(
    {"key": "stage", "details": "Development stage (container docker)"});
