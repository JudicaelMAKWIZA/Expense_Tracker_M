package com.example.expensetracker.feature_ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.R
import com.example.expensetracker.data.ExpenseDatabase
import com.example.expensetracker.data.ExpenseRepositoryImpl
import com.example.expensetracker.model.ExpenseEntity
import com.example.expensetracker.ui.theme.Zinc
import com.example.expensetracker.utils.Utils
import com.example.expensetracker.widget.ExpenseTextView

@Composable
fun HomeScreen(navController: NavController){
    val context = LocalContext.current
    val dao = ExpenseDatabase.getDatabase(context).expenseDao()
    val repository = ExpenseRepositoryImpl(dao)
    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(repository)
    )
    Surface(Modifier.fillMaxSize()) {
        ConstraintLayout (modifier = Modifier.fillMaxSize()){
            val (nameRow, list, card, topBar, add) = createRefs()
            Image(painter = painterResource(id = R.drawable.ic_topbar), contentDescription = null,
                modifier = Modifier.constrainAs(topBar){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                })
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 64.dp, start = 35.dp, end = 35.dp)
                    .constrainAs(nameRow) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }){
                Column {
                    ExpenseTextView(text = "Heureux de vous revoir !" , fontSize = 16.sp, color = Color.White)
                    ExpenseTextView(
                        text = "E-Tracker",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_notification),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
            val uiState by viewModel.uiState.collectAsState()
            CardItem(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .constrainAs(card) {
                    top.linkTo(nameRow.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, balance = uiState.balance, income = uiState.totalIncome, expenses = uiState.totalExpense)
            TransactionList(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(list) {
                    top.linkTo(card.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }, list = uiState.expenses, viewModel
            )
            
            Image(
                painter = painterResource(id = R.drawable.ic_addbutton),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(add){
                        bottom.linkTo(parent.bottom, margin =(56).dp)
                        end.linkTo(parent.end, margin = 16.dp)
                    }
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable {
                        navController.navigate("/add")
                    }
            )
        }
    }
}

@Composable
fun CardItem(
    modifier: Modifier,
    balance: String,
    income: String,
    expenses: String,
) {
    Column(modifier = modifier
        .padding(10.dp)
        .fillMaxWidth()
        .height(200.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(Zinc)
        .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Column {
                ExpenseTextView(
                    text = "Balance Totale",
                    fontSize = 16.sp,
                    //style = Typography.titleMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.size(8.dp))
                ExpenseTextView(
                    text = balance ,
                    //style = Typography.headlineLarge,
                    fontSize = 20.sp,
                    //fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
            Image(
                painter = painterResource(id = R.drawable.dots_menu),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            CardRowItem(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                title = "Solde",
                amount = income,
                imaget = R.drawable.ic_income
            )
            CardRowItem(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                title = "Dépense",
                amount = expenses,
                imaget = R.drawable.ic_expense
            )
        }
    }
}
@Composable
fun TransactionList(
    modifier: Modifier,
    list: List<ExpenseEntity>,
    viewModel: HomeViewModel
){
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp)
            ) {
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                ExpenseTextView(
                    text = "Dernières Transactions",
                    fontSize = 20.sp
                )
                ExpenseTextView(
                    text = "Voir plus",
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }
        items(items = list,
            key = { item -> item.id ?: 0 }) { item ->
            val icon = Utils.getItemIcon(item)
            val amount = if (item.type == "Solde") item.amount else item.amount * -1

            TransactionItem(
                title = item.title,
                amount = item.amount.toString(),
                icon = icon,
                date = Utils.formatStringDateToMonthDayYear(item.date),
                color = if (item.type == "Solde") Color.Green else Color.Red,
                Modifier
            )
        }
    }
}

@Composable
fun CardRowItem(modifier: Modifier, title: String, amount: String, imaget: Int) {
    Column(modifier = modifier) {
        Row {
            Image(
                painter = painterResource(id = imaget),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.size(8.dp))
            ExpenseTextView(
                text = title,
                //style = Typography.bodyLarge,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.size(4.dp))
        ExpenseTextView(
            text = amount,
            //style = Typography.titleLarge,
            color = Color.White)
    }
}

@Composable
fun TransactionItem(
    title: String,
    amount: String,
    icon: Int,
    date: String,
    color: Color,
    modifier: Modifier
){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        Row {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Column {
                ExpenseTextView(
                    text = title, fontSize = 16.sp
                    //style = Typography.titleMedium,
                    //color = Color.White
                )
                ExpenseTextView(
                    text = date,
                    fontSize = 12.sp,
                    //style = Typography.titleMedium,
                    //color = Color.White
                )
            }
        }
        ExpenseTextView(
            text = amount, fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterEnd),
            color = color,
            fontWeight = FontWeight.SemiBold
            //style = Typography.titleMedium,
        )
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewHomeScreen(){
    HomeScreen(rememberNavController())
}