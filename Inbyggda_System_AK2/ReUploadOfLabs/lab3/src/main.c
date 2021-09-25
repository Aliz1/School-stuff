#include <stdio.h>
#include <esp_task_wdt.h>
#include "soundgen.h"
#include "sampler.h"
#include <driver/adc.h>

/* C program to check if a tree is height-balanced or not */
#include <stdio.h> 
#include <stdlib.h> 
#define bool int 

/* A binary tree node has data, pointer to left child 
and a pointer to right child */
struct node { 
	int data; 
	struct node* left; 
	struct node* right; 
}; 

/* Returns the height of a binary tree */
int height(struct node* node); 

/* Returns true if binary tree with root as root is height-balanced */
bool isBalanced(struct node* root) 
{ 
	int lh; /* for height of left subtree */
	int rh; /* for height of right subtree */

	/* If tree is empty then return true */
	if (root == NULL) 
		return 1; 

	/* Get the height of left and right sub trees */
	lh = height(root->left); 
	rh = height(root->right); 

	if (abs(lh - rh) <= 1 && isBalanced(root->left) && isBalanced(root->right)) 
		return 1; 

	/* If we reach here then tree is not height-balanced */
	return 0; 
} 

/* UTILITY FUNCTIONS TO TEST isBalanced() FUNCTION */

/* returns maximum of two integers */
int max(int a, int b) 
{ 
	return (a >= b) ? a : b; 
} 

/* The function Compute the "height" of a tree. Height is the 
	number of nodes along the longest path from the root node 
	down to the farthest leaf node.*/
int height(struct node* node) 
{ 
	/* base case tree is empty */
	if (node == NULL) 
		return 0; 

	/* If tree is not empty then height = 1 + max of left 
	height and right heights */
	return 1 + max(height(node->left), height(node->right)); 
} 

/* Helper function that allocates a new node with the 
given data and NULL left and right pointers. */
struct node* newNode(int data) 
{ 
	struct node* node = (struct node*) 
		malloc(sizeof(struct node)); 
	node->data = data; 
	node->left = NULL; 
	node->right = NULL; 

	return (node); 
} 

void app_main() 
{ 
	struct node* root = newNode(20); 
    root->left = newNode(15); 
    root->right = newNode(25); 
    root->left->left = newNode(7); 
    root->left->right = newNode(12);
    root->left->right->right = newNode(12);
    root->left->left->left = newNode(5);
    root->left->left->right = newNode(8); 
    root->left->left->left->left = newNode(3);
    root->right->left = newNode(23);
    root->right->right = newNode(30);
    root->right->right->left = newNode(27);
    root->right->right->right = newNode(32);

	if (isBalanced(root)) 
		printf("Tree is balanced"); 
	else
		printf("Tree is not balanced"); 

	getchar(); 
   return 0;
} 
/*void app_main()
{
while (1)
{
    code 
int val = startSound(300);
     //int val = adc1_get_raw(ADC1_GPIO32_CHANNEL);
     //getFrequency();
     //vTaskDelay(pdTICKS_TO_MS(1000));
  printf("\n\n\n\n");
  printf("actual frequency is %d", val);
  printf("\n\n\n\n");
 startSampling(2000);
  }

     


   //int actualfreq = startSound(0);




 

    }*/