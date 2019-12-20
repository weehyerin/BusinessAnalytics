package com.example.card_business;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    SignInButton Google_Login;

    private static final int RC_SIGN_IN = 1000;
    private GoogleApiClient mGoogleApiClient;


    // 비밀번호 정규식
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$");

    // 파이어베이스 인증 객체 생성
    private FirebaseAuth firebaseAuth;

    // 이메일과 비밀번호
    private EditText editTextEmail;
    private EditText editTextPassword;

    private String email = "";
    private String password = "";

    // 페이스북 콜백 매니저
    private CallbackManager callbackManager;
    LoginButton buttonFacebook;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //페이스북 시
        // 파이어베이스 인증 객체 선언
        firebaseAuth = FirebaseAuth.getInstance();


        // 페이스북 콜백 등록
        callbackManager = CallbackManager.Factory.create();

        buttonFacebook = findViewById(R.id.facebook_login_button);
        buttonFacebook.setReadPermissions("email", "public_profile");
        buttonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        //페이스북 끝


        ///////////google

        //로그인 시도할 액티비티에서 유저데이터 요청
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        //구글로그인 버튼에 대한 이벤트
        SignInButton button = (SignInButton) findViewById(R.id.google_sign_in_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("클릭테스트");
                //이벤트 발생했을때, 구글로그인 버튼에 대한 (구글정보를 인텐트로 넘기는 값)

                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        ///////////

        editTextEmail = findViewById(R.id.email_edittext);
        editTextPassword = findViewById(R.id.password_edittext);

    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        // 페이스북 콜백 등록
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == RC_SIGN_IN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            if (result.isSuccess()) {
//                //구글 로그인 성공해서 파베에 인증
//                GoogleSignInAccount account = result.getSignInAccount();
//                firebaseAuthWithGoogle(account);
//
//            }
//            else{
//                //구글 로그인 실패
//            }
//        }
//    }

    //Intent Result 반환
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        //RC_SIGN_IN을 통해 로그인 확인여부 코드가 정상 전달되었다면
        if (requestCode == RC_SIGN_IN)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            //구글버튼 로그인 누르고 구글 사용자 확인시 실행
            if (result.isSuccess())
            {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();

                //구글 이용자 확인된 사람정보 파이어베이스로 넘기기
                firebaseAuthWithGoogle(account);


            } else {
            }
        }
    }



    //구글 파이어베이스로 값넘기기
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct)
    {
        //파이어베이스로 받은 구글사용자가 확인된 이용자의 값을 토큰으로 받고
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(getApplicationContext(), Details.class);

                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            intent.putExtra("userid",user.getUid()); /*송신*/
                            startActivity(intent);

                            Toast.makeText(MainActivity.this, "아이디 생성완료", Toast.LENGTH_SHORT).show();
                        } else
                        {

                            Toast.makeText(MainActivity.this, "아이디 생설실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

//    public void onStart()
//    { // 사용자가 현재 로그인되어 있는지 확인
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
//        if(currentUser!=null)
//        { // 만약 로그인이 되어있으면 다음 액티비티 실행
//            Intent intent = new Intent(getApplicationContext(), Details.class);
//            startActivity(intent);
//            finish();
//        }
//    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    // 페이스북 로그인 이벤트
    // 사용자가 정상적으로 로그인한 후 페이스북 로그인 버튼의 onSuccess 콜백 메소드에서 로그인한 사용자의
    // 액세스 토큰을 가져와서 Firebase 사용자 인증 정보로 교환하고,
    // Firebase 사용자 인증 정보를 사용해 Firebase에 인증.
    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 로그인 성공
                            Toast.makeText(MainActivity.this, R.string.success_login, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Details.class);

                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            intent.putExtra("userid",user.getUid()); /*송신*/
                            startActivity(intent);
                        } else {
                            // 로그인 실패저
                            Toast.makeText(MainActivity.this, R.string.failed_login, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void singUp(View view) {
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();

        if(isValidEmail() && isValidPasswd()) {
            createUser(email, password);
        }
    }

    public void signIn(View view) {
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();

        if(isValidEmail() && isValidPasswd()) {
            loginUser(email, password);
        }
    }

    // 이메일 유효성 검사
    private boolean isValidEmail() {
        if (email.isEmpty()) {
            // 이메일 공백
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // 이메일 형식 불일치
            return false;
        } else {
            return true;
        }
    }

    // 비밀번호 유효성 검사
    private boolean isValidPasswd() {
        if (password.isEmpty()) {
            // 비밀번호 공백
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            // 비밀번호 형식 불일치
            return false;
        } else {
            return true;
        }
    }

    // 회원가입
    private void createUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 회원가입 성공
                            Toast.makeText(MainActivity.this, R.string.success_signup, Toast.LENGTH_SHORT).show();
                        } else {
                            // 회원가입 실패
                            Toast.makeText(MainActivity.this, R.string.failed_signup, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // 로그인
    private void loginUser(String email, String password)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 로그인 성공
                            Toast.makeText(MainActivity.this, R.string.success_login, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Details.class);
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            intent.putExtra("userid",user.getUid()); /*송신*/
                            startActivity(intent);
                        } else {
                            // 로그인 실패
                            Toast.makeText(MainActivity.this, R.string.failed_login, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
