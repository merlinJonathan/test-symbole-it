package com.example.repositories;

import com.example.Model.ExcuseModel;
import com.example.dto.ExcuseDTO;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class ExcuseFirestoreRepository {

    private static CollectionReference collection;

    private static final String COLLECTION_NAME = "excuse";

    public ExcuseModel getExcuseByIdWithFireStore(String id) throws ExecutionException, InterruptedException {
        DocumentSnapshot document = getCollection().document(id).get().get();

        if(document.exists()) {
            return document.toObject(ExcuseModel.class);
        }

        return null;
    }

    public List<ExcuseModel> getAllExcuseWithFireStore() throws ExecutionException, InterruptedException {
        Iterable<DocumentReference> documentList = getCollection().listDocuments();

        List<ExcuseModel> result = new ArrayList<>();
        for(DocumentReference documentReference : documentList) {
            DocumentSnapshot document = documentReference.get().get();
            if(document.exists()) {
                ExcuseModel excuseModel = document.toObject(ExcuseModel.class);
                if(excuseModel != null) {
                    result.add(excuseModel);
                }
            }
        }

        return result;
    }

    public ExcuseModel postWithFirestore(ExcuseDTO excuseDTO) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = getCollection().document();
        ExcuseModel excuseModelToCreate = excuseDTO.toModel();
        excuseModelToCreate.setIdFirebase(documentReference.getId());
        ApiFuture<WriteResult> writeResultApiFuture = documentReference.set(excuseModelToCreate);

        writeResultApiFuture.get();

        DocumentSnapshot documentSnapshot = documentReference.get().get();
        if(documentReference.get().get().exists()) {
            return documentSnapshot.toObject(ExcuseModel.class);
        }
        return null;
    }

    private static CollectionReference getCollection() {
        if(collection == null) {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            collection = dbFirestore.collection(COLLECTION_NAME);
        }

        return collection;
    }
}
