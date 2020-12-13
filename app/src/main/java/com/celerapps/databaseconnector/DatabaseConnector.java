package com.celerapps.databaseconnector;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.celerapps.celermail.R;
import com.celerapps.celermail.home.views.LoginFragment;
import com.celerapps.celermail.inboxGroup.inbox.views.InboxActivity;
import com.celerapps.celermail.shared.interfaces.IAttachment;
import com.celerapps.celermail.shared.interfaces.IMail;
import com.celerapps.celermail.shared.interfaces.IMailAccount;
import com.celerapps.celermail.shared.interfaces.IMailAccountHeader;
import com.celerapps.celermail.shared.interfaces.IMailContainer;
import com.celerapps.celermail.shared.interfaces.IMailFolderInfo;
import com.celerapps.celermail.shared.models.Mail;
import com.celerapps.celermail.shared.models.MailAccount;
import com.celerapps.celermail.shared.models.MailContainer;
import com.celerapps.celermail.shared.models.MailFolderInfo;
import com.celerapps.celermail.utils.BaseActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class DatabaseConnector implements IDatabaseConnector {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser user;
    private String userId;
    private Context context = BaseActivity.context();
    private List<Mail> mailList = new ArrayList<>();
    private static DatabaseConnector databaseConnector;
    private List<String> emailsIdList = new ArrayList<>();
    private List<IMailContainer> mailContainers = new ArrayList<>();
    private IMailAccount selectedMailAccount;
    byte[] profilePhoto;
    private List<IMailFolderInfo> defaultFoldersList = new ArrayList<>();
    private List<IMailFolderInfo> customFoldersList = new ArrayList<>();

    public static DatabaseConnector getInstance() {

        if (databaseConnector == null) {

            databaseConnector = new DatabaseConnector();
        }
        return databaseConnector;
    }

    @Override
    public IMailAccount getSelectedMailAccount() {

        db.collection("users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()) {

                        String accountId = documentSnapshot.getId();
                        String name = (String) documentSnapshot.get("name");
                        String emailAddress = (String) documentSnapshot.get("email");
                        String password = (String) documentSnapshot.get("password");
                        String gender = (String) documentSnapshot.get("gender");
                        String countrie = (String) documentSnapshot.get("countrie");
                        Bitmap image = BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.user_circle);
                        Blob dbImage = (Blob) documentSnapshot.get("image");
                        if (dbImage != null) {
                            byte[] imageBytes = dbImage.toBytes();
                            image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                        }
                        String folderId = (String) documentSnapshot.get("folderId");
                        long birthdate = (long) documentSnapshot.get("birthdate");
                        String phone = (String) documentSnapshot.get("phone");
                        selectedMailAccount = new MailAccount(accountId, name, emailAddress, password, gender, countrie, image, defaultFoldersList, customFoldersList, folderId, birthdate, phone);

                        db.collection("users").document(userId).collection("defaultFolders").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {

                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        if (!defaultFoldersList.contains(document.toObject(MailFolderInfo.class))) {
                                            String folderId = (String) document.get("folderId");
                                            String folderName = (String) document.get("folderName");
                                            boolean isUserCreated = document.getBoolean("isUserCreated");

                                            IMailFolderInfo mfi = new MailFolderInfo(folderId, folderName, isUserCreated);
                                            defaultFoldersList.add(mfi);

                                        }
                                    }
                                    selectedMailAccount.setDefaultFolders(defaultFoldersList);
                                    Log.d(TAG, defaultFoldersList.toString());
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });

                        db.collection("users").document(userId).collection("customFolders").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {

                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        if (!customFoldersList.contains(document.toObject(MailFolderInfo.class))) {
                                            String folderId = (String) document.get("folderId");
                                            String folderName = (String) document.get("folderName");
                                            boolean isUserCreated = true;
                                            if (document.getBoolean("isUserCreated") != null) {
                                                isUserCreated = document.getBoolean("isUserCreated");
                                            }
                                            IMailFolderInfo mfi = new MailFolderInfo(folderId, folderName, isUserCreated);
                                            customFoldersList.add(mfi);

                                        }
                                    }
                                    selectedMailAccount.setCustomFolders(customFoldersList);
                                    Log.d(TAG, customFoldersList.toString());
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });
                        Log.d(TAG, "DocumentSnapshot data: " + documentSnapshot.getData());
                    } else {
                        Log.d(TAG, "El usuario no existe.");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });


        return selectedMailAccount;
    }

    public DatabaseConnector() {
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user != null) {
            userId = this.getUserId();
            if (selectedMailAccount == null) {
                selectedMailAccount = getSelectedMailAccount();
            }
        }


    }

    public DatabaseConnector(Context context) {
        this.context = context;
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user != null) {
            userId = this.getUserId();
        }
        if (selectedMailAccount == null) {
            selectedMailAccount = getSelectedMailAccount();
        }
    }


    @Override
    public IMail getMail(String mailId) {
        final IMail[] mail = new Mail[1];
        db.collection("emails").document(mailId).get().addOnCompleteListener(
                task -> {
                    if (task.isComplete()) {
                        String id = (String) task.getResult().get("mailId");
                        String senderName = (String) task.getResult().get("senderName");
                        String senderAddress = (String) task.getResult().get("senderAddress");
                        String receiverAddress = (String) task.getResult().get("receiverAddress");
                        String cc = (String) task.getResult().get("cc");
                        String cco = (String) task.getResult().get("cco");
                        String subject = (String) task.getResult().get("subject");
                        String bodyText = (String) task.getResult().get("bodyText");
                        long dateTimeInMs = (long) task.getResult().get("dateTimeInMs");
                        boolean isImportant = (boolean) task.getResult().get("isImportant");
                        List<IAttachment> attachments = null;
                        final IMail mailDb = new Mail(id, senderName, senderAddress, receiverAddress, cc, cco, subject, bodyText, dateTimeInMs, isImportant, attachments);
                        mail[0] = mailDb;
                    } else {
                        Log.e(TAG, "Error al crear carpeta.");
                    }
                }
        );

        return mail[0];
    }

    @Override
    public List<IMailContainer> getMailContainers(String folderId, boolean isUserCreated) {
        List<IMailContainer> mailContainers = new ArrayList<>();
        String collectionName;
        if (!isUserCreated) {
            collectionName = "defaultFolders";
        } else {
            collectionName = "customFolders";
        }
        db.collection("users").document(userId).collection(collectionName).document(folderId).collection("emailsId").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String mailId = (String) document.get("mailId");
                        IMail mail = getMail(mailId);
                        IMailContainer container = new MailContainer(mail);
                        mailContainers.add(container);
                    }
                    Log.d(TAG, customFoldersList.toString());
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });


        return mailContainers;
    }

    @Override
    public IMailAccountHeader getMailAccountHeader() {
        return this.getSelectedMailAccount().getMailAccountHeader();
    }

    @Override
    public void createMail(IMail mMail) {
        mMail.setId();
        db.collection("emails").add(mMail).addOnSuccessListener(command -> {
            Log.d(TAG, "Mail sended successfully!");

            db.collection("users").document(getUserId()).collection("defaultFolders").document("4").collection("emailsId").add(mMail.getId()).addOnSuccessListener(command2 -> {
            }).addOnFailureListener(e -> {
            });

        }).addOnFailureListener(e -> {
            Log.w(TAG, "Error sending mail.", e);
            Toast t = Toast.makeText(context, "Fallo al enviar el correo.", Toast.LENGTH_SHORT);
            t.show();
        });

    }

    @Override
    public void createAccountFolder(String folderName) {
        IMailFolderInfo mfi = new MailFolderInfo(folderName);
        db.collection("users").document(getUserId()).collection("customFolders").add(mfi).addOnCompleteListener(
                task -> {
                    if (task.isComplete()) {
                        Log.d(TAG, "Carpeta creada correctamente");
                    } else {
                        Log.e(TAG, "Error al crear carpeta.");
                    }
                }
        );
    }


    public void createAccount(@NotNull IMailAccount mailAccount) {


        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(mailAccount.getEmailAddress(), mailAccount.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "/////////////createUserWithEmail:success//////////////////////////////");


                            Map<String, Object> map = new HashMap<>();
                            map.put("birthdate", mailAccount.getBirthdate());
                            map.put("countrie", mailAccount.getCountrie());
                            map.put("email", mailAccount.getEmailAddress());
                            map.put("folderId", "0");
                            map.put("gender", mailAccount.getGender());
                            map.put("image", Blob.fromBytes(profilePhoto));
                            map.put("name", mailAccount.getName());
                            map.put("password", mailAccount.getPassword());
                            map.put("phone", mailAccount.getPhone());

                            String id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                            map.put("uid", id);
                            db.collection("users").document(id)
                                    .set(map)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d(TAG, "User registerd successfully!");
                                            Toast t = Toast.makeText(context, "¡Registrad@ correctamente!", Toast.LENGTH_SHORT);
                                            t.show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Error registering user.", e);
                                            Toast t = Toast.makeText(context, "Fallo al registrar en la base de datos.", Toast.LENGTH_SHORT);
                                            t.show();
                                        }
                                    });
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Firestore user authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    public void signIn(String email, String password) {

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            userId = getUserId();
                            //Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                            LoginFragment.getFragmentActivity().startActivity(new Intent(LoginFragment.getFragmentActivity(), InboxActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(context, "Email o contraseña incorrectos, prueba de nuevo.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // [END sign_in_with_email]
    }


    public void signOut() {
        mAuth.signOut();
    }

    public String getUserId() {
        FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
        if (mFirebaseUser != null) {
            userId = mFirebaseUser.getUid(); //Do what you need to do with the id
        }
        return userId;
    }
}
