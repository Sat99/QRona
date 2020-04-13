const express = require("express");
const port = 3000;
const app = express();
app.listen(port, function() {
  console.log("Server is running on " + port + " port");
});
app.get("/", function(req, res) {
  res.send("<h1>QRona</h1>");
});
