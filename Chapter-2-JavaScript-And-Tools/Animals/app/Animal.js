// Animal is the top of the object hierarchy
function Animal() {}

// Define a speak function that have specific implementations in subclasses
Animal.prototype.speak = function() {
    return "Animal is speaking.";
};

