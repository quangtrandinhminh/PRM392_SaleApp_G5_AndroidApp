package quangtdm.example.saleapp_g5.Network;

public class WebAPIEndpoint {
    private static final String BASE_API = "/api";
    
    public static class Authentication
    {
        private static final String BASE_URL = BASE_API + "/auth";
        public static final String Hello = BASE_URL + "/hello";
        public static final String Register = BASE_URL + "/register";
        public static final String Login = BASE_URL + "/authentication";
    }

    public static class User
    {
        private static final String BASE_URL = BASE_API + "/user";
        public static final String GetUsers = BASE_URL;
        public static final String GetUser = BASE_URL + "/{id}";
        public static final String CreateUser = BASE_URL;
        public static final String UpdateUser = BASE_URL;
        public static final String DeleteUser = BASE_URL + "/{id}";
    }

    public static class Product
    {
        private static final String BASE_URL = BASE_API + "/product";
        public static final String GetProducts = BASE_URL;
        public static final String GetProductById = BASE_URL + "/{id}";
        public static final String CreateProduct = BASE_URL;
        public static final String UpdateProduct = BASE_URL;
        public static final String DeleteProduct = BASE_URL + "/{id}";
    }

    public static class Category
    {
        private static final String BASE_URL = BASE_API + "/category";
        public static final String GetCategories = BASE_URL;
        public static final String GetCategory = BASE_URL + "/{id}";
        public static final String CreateCategory = BASE_URL;
        public static final String UpdateCategory = BASE_URL;
        public static final String DeleteCategory = BASE_URL + "/{id}";
    }

    public static class Order
    {
        private static final String BASE_URL = BASE_API + "/order";
        public static final String GetOrders = BASE_URL;
        public static final String GetOrder = BASE_URL + "/{id}";
        public static final String CreateOrder = BASE_URL;
        public static final String UpdateOrder = BASE_URL;
        public static final String DeleteOrder = BASE_URL + "/{id}";
    }

    public static class Cart
    {
        private static final String BASE_URL = BASE_API + "/cart";
        public static final String GetCarts = BASE_URL;
        public static final String GetCart = BASE_URL + "/{id}";
        public static final String CreateCart = BASE_URL;
        public static final String UpdateCart = BASE_URL;
        public static final String DeleteCart = BASE_URL + "/{id}";
    }

    public static class CartItem
    {
        private static final String BASE_URL = BASE_API + "/cartItem";
        public static final String GetCartItems = BASE_URL;
        public static final String GetCartItem = BASE_URL + "/{id}";
        public static final String CreateCartItem = BASE_URL;
        public static final String UpdateCartItem = BASE_URL;
        public static final String DeleteCartItem = BASE_URL + "/{id}";
    }

    public static class ProductCategory
    {
        private static final String BASE_URL = BASE_API + "/productCategory";
        public static final String GetProductCategories = BASE_URL;
        public static final String GetProductCategory = BASE_URL + "/{id}";
        public static final String CreateProductCategory = BASE_URL;
        public static final String UpdateProductCategory = BASE_URL;
        public static final String DeleteProductCategory = BASE_URL + "/{id}";
    }

    public static class OrderItem
    {
        private static final String BASE_URL = BASE_API + "/orderItem";
        public static final String GetOrderItems = BASE_URL;
        public static final String GetOrderItem = BASE_URL + "/{id}";
        public static final String CreateOrderItem = BASE_URL;
        public static final String UpdateOrderItem = BASE_URL;
        public static final String DeleteOrderItem = BASE_URL + "/{id}";
    }

    public static class ChatMessage
    {
        private static final String BASE_URL = BASE_API + "/chatMessage";
        public static final String GetChatMessages = BASE_URL;
        public static final String GetChatMessage = BASE_URL + "/{id}";
        public static final String CreateChatMessage = BASE_URL;
        public static final String UpdateChatMessage = BASE_URL;
        public static final String DeleteChatMessage = BASE_URL + "/{id}";
    }

    public static class Notification
    {
        private static final String BASE_URL = BASE_API + "/notification";
        public static final String GetNotifications = BASE_URL;
        public static final String CreateNotification = BASE_URL;
    }

    public static class Payment
    {
        private static final String BASE_URL = BASE_API + "/payment";
        public static final String VnpayUrl = BASE_URL + "/vnpay/payment-url";
        public static final String VnpayExecute = BASE_URL + "vnpay/payment-execute";
    }
}
