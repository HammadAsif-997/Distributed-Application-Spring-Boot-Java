const express = require('express');
const fs = require('fs');
const path = require('path');
const app = express();
const PORT = 8080;

// Middleware to parse JSON request body
app.use(express.json());

// Helper function to read products data from products.json
const readProductsData = () => {
  const dataPath = path.join(__dirname, 'response/data/products.json');
  const jsonData = fs.readFileSync(dataPath);
  return JSON.parse(jsonData);
};

// Helper function to write products data back to products.json
const writeProductsData = (data) => {
  const dataPath = path.join(__dirname, 'response/data/products.json');
  fs.writeFileSync(dataPath, JSON.stringify(data, null, 2));
};

// GET: Fetch all products
app.get('/products', (req, res) => {
  const products = readProductsData();
  res.json(products);
});

// GET: Fetch a single product by ID
app.get('/products/:id', (req, res) => {
  const products = readProductsData();
  const product = products.find(p => p.id === parseInt(req.params.id));

  if (product) {
    res.json(product);
  } else {
    res.status(404).json({ error: 'Product not found' });
  }
});

// POST: Create a new product
app.post('/products', (req, res) => {
  const products = readProductsData();
  const newProduct = req.body;
  newProduct.id = products.length ? Math.max(...products.map(p => p.id)) + 1 : 1; // Generate new ID

  products.push(newProduct);
  writeProductsData(products);

  res.status(201).json(newProduct);
});

// PUT: Update an existing product by ID
app.put('/products/:id', (req, res) => {
  const products = readProductsData();
  const productIndex = products.findIndex(p => p.id === parseInt(req.params.id));

  if (productIndex !== -1) {
    const updatedProduct = { ...products[productIndex], ...req.body };
    products[productIndex] = updatedProduct;
    writeProductsData(products);
    res.json(updatedProduct);
  } else {
    res.status(404).json({ error: 'Product not found' });
  }
});

// DELETE: Delete a product by ID
app.delete('/products/:id', (req, res) => {
  const products = readProductsData();
  const productIndex = products.findIndex(p => p.id === parseInt(req.params.id));

  if (productIndex !== -1) {
    products.splice(productIndex, 1);
    writeProductsData(products);
    res.status(204).end(); // No content
  } else {
    res.status(404).json({ error: 'Product not found' });
  }
});

// Start the server
app.listen(PORT, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
});
