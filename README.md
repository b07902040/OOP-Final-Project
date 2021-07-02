# HearthClone

### 執行程式
* local端開兩個視窗
    ```
    bash runLocal.sh
    ```
    起始兩個視窗會疊在一起，由左下顯示Your Turn的視窗開始操作。
    ![](https://i.imgur.com/wtMf8wW.png)

* server - 2 clients

    先開Server，再開Clients
    
    Server：在工作站linux3上 server預設開port 2021，執行下列指令
    ```
    bash runServer.sh $(port)
    ```
    Client：分別在兩台機器，預設輸入linux3 ip = 140.112.30.34，port = 2021
    ```
    bash runClient.sh $(ip) $(port)
    ```
