#include <stdio.h>
#include <string.h>
#define MAX_ORDERS 100

// Structure for an Order
struct Order {
    char name[50];
    char details[150];
};

// Queue for pending orders
struct Queue {
    struct Order orders[MAX_ORDERS];
    int front;
    int rear;
};

// Stack for completed orders
struct Stack {
    struct Order orders[MAX_ORDERS];
    int top;
};

// Initialize queue
void initializeQueue(struct Queue* q) {
    q->front = 0;
    q->rear = 0;
}

// Initialize stack
void initializeStack(struct Stack* s) {
    s->top = -1;
}

// Queue and stack operations
int isQueueEmpty(struct Queue* q) { return q->front == q->rear; }
int isQueueFull(struct Queue* q) { return q->rear == MAX_ORDERS; }
int isStackEmpty(struct Stack* s) { return s->top == -1; }
int isStackFull(struct Stack* s) { return s->top == MAX_ORDERS - 1; }

void enqueue(struct Queue* q, char name[], char details[]) {
    if (isQueueFull(q)) {
        printf("Queue is full! Cannot place more orders.\n\n");
        return;
    }
    strcpy(q->orders[q->rear].name, name);
    strcpy(q->orders[q->rear].details, details);
    q->rear++;
    printf("Order placed successfully!\n\n");
}

struct Order dequeue(struct Queue* q) {
    struct Order temp = q->orders[q->front];
    q->front++;
    return temp;
}

void push(struct Stack* s, struct Order order) {
    if (isStackFull(s)) {
        printf("Stack is full! Cannot store more completed orders.\n\n");
        return;
    }
    s->top++;
    s->orders[s->top] = order;
}

// View functions
void viewPendingOrders(struct Queue* q) {
    if (isQueueEmpty(q)) {
        printf("No pending orders.\n\n");
        return;
    }
    printf("Pending Orders:\n");
    for (int i = q->front; i < q->rear; i++) {
        printf("%d. %s - %s\n", i - q->front + 1, q->orders[i].name, q->orders[i].details);
    }
    printf("\n");
}


void viewCompletedOrders(struct Stack* s) {
    if (isStackEmpty(s)) {
        printf("No completed orders.\n\n");
        return;
    }
    printf("Completed Orders (Most Recent First):\n");
    for (int i = s->top; i >= 0; i--) {
        printf("%d. %s - %s\n", s->top - i + 1, s->orders[i].name, s->orders[i].details);
    }
    printf("\n");
}

// Function to display main food menu
void showFoodMenu() {
    printf("------ FOOD MENU ------\n");
    printf("1. Burger - ₹120\n");
    printf("2. Pizza - ₹250\n");
    printf("3. Sandwich - ₹90\n");
    printf("4. Pasta - ₹150\n");
    printf("5. Fried Rice - ₹130\n");
    printf("6. Noodles - ₹140\n");
    printf("7. Grilled Chicken - ₹280\n");
    printf("8. Coffee - ₹80\n");
    printf("9. Cold Drink - ₹60\n");
    printf("10. Milkshake - ₹100\n");
    printf("------------------------\n\n");
}

// Function to display specific add-on menus
void showAddonMenu(int choice) {
    switch (choice) {
        case 1:
            printf("--- Add-ons for Burger ---\n");
            printf("1. Extra Cheese - ₹30\n2. Extra Patty - ₹70\n3. Fries Combo - ₹60\n4. None\n");
            break;
        case 2:
            printf("--- Add-ons for Pizza ---\n");
            printf("1. Extra Cheese - ₹40\n2. Garlic Bread - ₹70\n3. Cold Drink - ₹40\n4. None\n");
            break;
        case 3:
            printf("--- Add-ons for Sandwich ---\n");
            printf("1. Extra Cheese - ₹25\n2. Mayonnaise - ₹20\n3. Fries - ₹50\n4. None\n");
            break;
        case 4:
            printf("--- Add-ons for Pasta ---\n");
            printf("1. Garlic Bread - ₹70\n2. Extra Sauce - ₹30\n3. Cold Drink - ₹40\n4. None\n");
            break;
        case 5:
            printf("--- Add-ons for Fried Rice ---\n");
            printf("1. Chicken Add-on - ₹60\n2. Egg Add-on - ₹30\n3. Sauce - ₹20\n4. None\n");
            break;
        case 6:
            printf("--- Add-ons for Noodles ---\n");
            printf("1. Chicken Add-on - ₹60\n2. Egg Add-on - ₹30\n3. Sauce - ₹20\n4. None\n");
            break;
        case 7:
            printf("--- Add-ons for Grilled Chicken ---\n");
            printf("1. Masala Upgrade - ₹40\n2. Extra Piece - ₹100\n3. Cold Drink - ₹40\n4. None\n");
            break;
        case 8:
            printf("--- Add-ons for Coffee ---\n");
            printf("1. Cream - ₹20\n2. Chocolate Syrup - ₹25\n3. Extra Sugar - ₹10\n4. None\n");
            break;
        case 9:
            printf("--- Add-ons for Cold Drink ---\n");
            printf("1. Ice Cubes - ₹10\n2. Lemon Slice - ₹10\n3. Large Cup - ₹20\n4. None\n");
            break;
        case 10:
            printf("--- Add-ons for Milkshake ---\n");
            printf("1. Ice Cream Scoop - ₹40\n2. Chocolate Syrup - ₹30\n3. Whipped Cream - ₹25\n4. None\n");
            break;
    }
}

// Function to display Milkshake flavour menu
void showMilkshakeFlavours() {
    printf("--- Choose Milkshake Flavour ---\n");
    printf("1. Chocolate\n2. Vanilla\n3. Strawberry\n4. Oreo\n5. Banana\n");
    printf("-------------------------------\n");
}

// Main Menu
void menu() {
    struct Queue pendingOrders;
    struct Stack completedOrders;
    initializeQueue(&pendingOrders);
    initializeStack(&completedOrders);


    int choice, foodChoice, addonChoice, flavourChoice;
    char name[50];
    char details[150];

    while (1) {
        printf("=== Restaurant Order Management System ===\n");
        printf("1. Place Order\n");
        printf("2. View Pending Orders\n");
        printf("3. Process Order\n");
        printf("4. View Completed Orders\n");
        printf("5. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);
        getchar(); // clear newline

        switch (choice) {
            case 1:
                showFoodMenu();
                printf("Enter customer name: ");
                fgets(name, sizeof(name), stdin);
                name[strcspn(name, "\n")] = 0; // remove newline

                printf("Enter item number to order: ");
                scanf("%d", &foodChoice);
                getchar();

                strcpy(details, "");

                if (foodChoice == 10) {
                    showMilkshakeFlavours();
                    printf("Enter flavour number: ");
                    scanf("%d", &flavourChoice);
                    getchar();

                    switch (flavourChoice) {
                        case 1: strcat(details, "Chocolate Milkshake"); break;
                        case 2: strcat(details, "Vanilla Milkshake"); break;
                        case 3: strcat(details, "Strawberry Milkshake"); break;
                        case 4: strcat(details, "Oreo Milkshake"); break;
                        case 5: strcat(details, "Banana Milkshake"); break;
                        default: strcat(details, "Milkshake"); break;
                    }
                } else {
                    char foodItems[10][30] = {
                        "Burger", "Pizza", "Sandwich", "Pasta", "Fried Rice",
                        "Noodles", "Grilled Chicken", "Coffee", "Cold Drink", "Milkshake"
                    };
                    strcat(details, foodItems[foodChoice - 1]);
                }

                printf("\n");
                showAddonMenu(foodChoice);
                printf("Enter extra item number: ");
                scanf("%d", &addonChoice);
                getchar();

                switch (addonChoice) {
                    case 1: strcat(details, " with Extra Option 1"); break;
                    case 2: strcat(details, " with Extra Option 2"); break;
                    case 3: strcat(details, " with Extra Option 3"); break;
                    default: break;
                }

                enqueue(&pendingOrders, name, details);
                break;

            case 2:
                viewPendingOrders(&pendingOrders);
                break;

            case 3:
                if (isQueueEmpty(&pendingOrders)) {
                    printf("No pending orders to process.\n\n");
                } else {
                    struct Order processedOrder = dequeue(&pendingOrders);
                    printf("Order for %s has been processed and completed!\n\n", processedOrder.name);
                    push(&completedOrders, processedOrder);
                }
                break;

            case 4:
                viewCompletedOrders(&completedOrders);
                break;

            case 5:
                printf("Exiting... Thank you!\n");
                return;

            default:
                printf("Invalid choice. Please try again.\n\n");
        }
    }
}

int main() {
    menu();
    return 0;
}
