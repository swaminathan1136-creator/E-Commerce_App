from flask import Flask, request, jsonify
from flask_cors import CORS
from config import get_db_connection

app = Flask(__name__)
CORS(app)  # Allow Android app to access APIs

# ====================== GET RESTAURANTS ======================
@app.route('/get_restaurants', methods=['GET'])
def get_restaurants():
    conn = get_db_connection()
    cursor = conn.cursor(dictionary=True)
    cursor.execute("SELECT * FROM restaurants ORDER BY rating DESC")
    restaurants = cursor.fetchall()
    cursor.close()
    conn.close()
    return jsonify(restaurants)

# ====================== GET CATEGORIES ======================
@app.route('/get_categories', methods=['GET'])
def get_categories():
    conn = get_db_connection()
    cursor = conn.cursor(dictionary=True)
    cursor.execute("SELECT * FROM categories ORDER BY id ASC")
    categories = cursor.fetchall()
    cursor.close()
    conn.close()
    return jsonify(categories)

# ====================== GET FOOD ITEMS ======================
@app.route('/get_food_items', methods=['GET'])
def get_food_items():
    restaurant_id = request.args.get('restaurant_id', 0, type=int)

    conn = get_db_connection()
    cursor = conn.cursor(dictionary=True)

    if restaurant_id > 0:
        cursor.execute(
            "SELECT * FROM food_items WHERE restaurant_id = %s ORDER BY is_best_seller DESC, rating DESC",
            (restaurant_id,)
        )
    else:
        cursor.execute("SELECT * FROM food_items ORDER BY is_best_seller DESC, rating DESC")

    food_items = cursor.fetchall()
    cursor.close()
    conn.close()
    return jsonify(food_items)

# ====================== REGISTER ======================
@app.route('/register', methods=['POST'])
def register():
    data = request.get_json()
    full_name = data.get('full_name', '')
    email = data.get('email', '')
    phone = data.get('phone', '')
    password = data.get('password', '')

    if not full_name or not email or not password:
        return jsonify({"success": False, "message": "All fields are required"})

    conn = get_db_connection()
    cursor = conn.cursor(dictionary=True)

    # Check if email already exists
    cursor.execute("SELECT id FROM users WHERE email = %s", (email,))
    if cursor.fetchone():
        cursor.close()
        conn.close()
        return jsonify({"success": False, "message": "Email already registered"})

    # Insert new user
    cursor.execute(
        "INSERT INTO users (full_name, email, phone, password) VALUES (%s, %s, %s, %s)",
        (full_name, email, phone, password)
    )
    conn.commit()
    user_id = cursor.lastrowid

    cursor.close()
    conn.close()
    return jsonify({"success": True, "message": "Registration successful", "user_id": user_id})

# ====================== LOGIN ======================
@app.route('/login', methods=['POST'])
def login():
    data = request.get_json()
    email = data.get('email', '')
    password = data.get('password', '')

    if not email or not password:
        return jsonify({"success": False, "message": "Email and password are required"})

    conn = get_db_connection()
    cursor = conn.cursor(dictionary=True)

    cursor.execute(
        "SELECT id, full_name, email, phone FROM users WHERE email = %s AND password = %s",
        (email, password)
    )
    user = cursor.fetchone()

    cursor.close()
    conn.close()

    if user:
        return jsonify({"success": True, "message": "Login successful", "user": user})
    else:
        return jsonify({"success": False, "message": "Invalid email or password"})

# ====================== ADD TO CART ======================
@app.route('/add_to_cart', methods=['POST'])
def add_to_cart():
    data = request.get_json()
    user_id = data.get('user_id', 0)
    food_item_id = data.get('food_item_id', 0)
    quantity = data.get('quantity', 1)

    if user_id == 0 or food_item_id == 0:
        return jsonify({"success": False, "message": "User ID and Food Item ID are required"})

    conn = get_db_connection()
    cursor = conn.cursor(dictionary=True)

    # Check if item already in cart
    cursor.execute(
        "SELECT id, quantity FROM cart WHERE user_id = %s AND food_item_id = %s",
        (user_id, food_item_id)
    )
    existing = cursor.fetchone()

    if existing:
        new_quantity = existing['quantity'] + quantity
        cursor.execute(
            "UPDATE cart SET quantity = %s WHERE id = %s",
            (new_quantity, existing['id'])
        )
        message = "Cart updated"
    else:
        cursor.execute(
            "INSERT INTO cart (user_id, food_item_id, quantity) VALUES (%s, %s, %s)",
            (user_id, food_item_id, quantity)
        )
        message = "Item added to cart"

    conn.commit()
    cursor.close()
    conn.close()
    return jsonify({"success": True, "message": message})

# ====================== GET CART ======================
@app.route('/get_cart', methods=['GET'])
def get_cart():
    user_id = request.args.get('user_id', 0, type=int)

    if user_id == 0:
        return jsonify({"success": False, "message": "User ID is required"})

    conn = get_db_connection()
    cursor = conn.cursor(dictionary=True)

    cursor.execute("""
        SELECT c.id, c.quantity, f.id as food_item_id, f.name, f.price, f.image, f.rating 
        FROM cart c 
        JOIN food_items f ON c.food_item_id = f.id 
        WHERE c.user_id = %s
    """, (user_id,))

    cart_items = cursor.fetchall()
    cursor.close()
    conn.close()
    return jsonify(cart_items)

# ====================== RUN SERVER ======================
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5001, debug=True)