package com.example.ojtaadaassignment12.presenter.ui.user;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.example.ojtaadaassignment12.App;
import com.example.ojtaadaassignment12.R;
import com.example.ojtaadaassignment12.domain.model.User;
import com.example.ojtaadaassignment12.presenter.utils.MyBitmapUtil;
import com.example.ojtaadaassignment12.presenter.utils.MyConstants;
import com.example.ojtaadaassignment12.presenter.utils.MyValidator;

import java.util.Objects;

import javax.inject.Inject;


public class EditProfileFragment extends Fragment {
    @Inject
    UserProfileViewModel userProfileViewModel;

    //Views
    Button mBtnCancel, mBtnDone;
    ImageView mIvAvatar;
    EditText mEtName, mEtEmail, mEtDob;
    RadioGroup mRgGender;
    TextView mTvError;
    private User mUser;

    private ActivityResultLauncher takePictureLauncher;
    private ActivityResultLauncher choosePictureLauncher;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getActivity().getApplication()).getAppComponent().inject(this);

        mUser = Objects.requireNonNull(userProfileViewModel.getUser(MyConstants.USER_ID).getValue()).clone();

        takePictureLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                rs -> {
                    if (rs.getResultCode() == getActivity().RESULT_OK) {
                        Intent data = rs.getData();
                        if (data != null && data.getExtras() != null) {
                            // Lấy Bitmap từ extras
                            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                            if (imageBitmap != null) {
                                String base64Image = MyBitmapUtil.convertBitmapToBase64(imageBitmap);
                                mUser.setAvatar(base64Image);
                                mIvAvatar.setImageBitmap(imageBitmap);
                            }
                        }
                    }
                }
        );

        choosePictureLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                rs -> {
                    if (rs.getResultCode() == getActivity().RESULT_OK) {
                        if (rs.getResultCode() == getActivity().RESULT_OK && rs.getData() != null) {
                            Uri selectedImageUri = rs.getData().getData();
                            try {
                                // Lấy Bitmap từ URI
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                                mIvAvatar.setImageBitmap(bitmap); // Hiển thị hình ảnh
                                mUser.setAvatar(MyBitmapUtil.convertBitmapToBase64(bitmap));
                            } catch (Exception e) {
                                Toast.makeText(getActivity(), "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        mBtnCancel = view.findViewById(R.id.form_btn_cancel);
        mBtnDone = view.findViewById(R.id.form_btn_done);
        mIvAvatar = view.findViewById(R.id.form_avatar);
        mEtName = view.findViewById(R.id.form_et_name);
        mEtEmail = view.findViewById(R.id.form_et_email);
        mEtDob = view.findViewById(R.id.form_et_dob);
        mRgGender = view.findViewById(R.id.form_rg_gender);
        mTvError = view.findViewById(R.id.form_tv_error);

        mIvAvatar.setImageBitmap(MyBitmapUtil.decodeBase64ToBitmap(mUser.getAvatar()));
        mEtName.setText(mUser.getFullName());
        mEtEmail.setText(mUser.getEmail());
        mEtDob.setText(mUser.getDob());
        mRgGender.check((mUser.getGender().equals("male")) ? R.id.form_rb_male : R.id.form_rb_female);

        mBtnDone.setOnClickListener(v -> handleSubmitForm());
        mBtnCancel.setOnClickListener(v -> getParentFragmentManager().popBackStack());
        mIvAvatar.setOnClickListener(this::showPopupMenuForImage);

        return view;
    }

    private void showPopupMenuForImage(View view) {
        // Tạo Popup Menu
        PopupMenu popupMenu = new PopupMenu(getActivity(), view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_get_image, popupMenu.getMenu());

        // Thiết lập listener cho các mục trong menu
        popupMenu.setOnMenuItemClickListener(
                item -> {
                    if (item.getItemId() == R.id.menu_item_camera) {
                        Log.d("qz", "Camera button clicked");
                        handleTakePicture();
                        return true;
                    }

                    if (item.getItemId() == R.id.menu_item_galary) {
                        Log.d("qz", "Galary button clicked");
                        handleChooseFromGalary();
                        return true;
                    }
                    return false;

                });

        // Hiển thị Popup Menu
        popupMenu.show();
    }

    private void handleChooseFromGalary() {
        Intent choosePictureIntent = new Intent(
                Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        choosePictureLauncher.launch(choosePictureIntent);
    }

    private void handleTakePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureLauncher.launch(takePictureIntent);
    }


    private void handleSubmitForm() {

        User userUpdated = getUserFromForm();
        if (userUpdated == null) return;

        if (mTvError.getVisibility() == View.VISIBLE) {
            mTvError.setVisibility(View.GONE);
        }

        userProfileViewModel.saveUser(MyConstants.USER_ID, userUpdated);
        userProfileViewModel.getUserLiveData().setValue(userUpdated);

        requireActivity().getSupportFragmentManager().popBackStack();
//
//        Bundle rs = new Bundle();
//        rs.putSerializable("user", userUpdated);
//        getParentFragmentManager().setFragmentResult("user_updated", rs);
    }

    private User getUserFromForm() {
        String name = mEtName.getText().toString();
        String email = mEtEmail.getText().toString();
        String dob = mEtDob.getText().toString();
        int genderId = mRgGender.getCheckedRadioButtonId();

        if (name.isEmpty() || email.isEmpty() || dob.isEmpty() || genderId == -1) {
            showFormError("Please fill all form");
            return null;
        }
        ;

        if (!MyValidator.isDateValid(dob)) {
            showFormError("Invalid date, please follow this format: yyyy/mm/dd");
            return null;
        }

        String gender = (genderId == R.id.form_rb_male) ? "male" : "female";
        return new User(name, email, dob, gender, mUser.getAvatar());
    }

    private void showFormError(String message) {
        mTvError.setText(message);
        mTvError.setVisibility(View.VISIBLE);
    }


}