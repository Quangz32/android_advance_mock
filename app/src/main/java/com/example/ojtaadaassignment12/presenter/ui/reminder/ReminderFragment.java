package com.example.ojtaadaassignment12.presenter.ui.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.work.WorkManager;

import com.example.ojtaadaassignment12.App;
import com.example.ojtaadaassignment12.R;
import com.example.ojtaadaassignment12.databinding.FragmentReminderBinding;
import com.example.ojtaadaassignment12.domain.model.Reminder;
import com.example.ojtaadaassignment12.presenter.utils.MyConstants;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;


public class ReminderFragment extends Fragment {
    FragmentReminderBinding binding;

    @Inject
    ReminderViewModel reminderViewModel;

    public ReminderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) requireActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReminderBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        reminderViewModel.getRemindersLiveData().observe(getViewLifecycleOwner(), reminders -> {
            binding.reminderTable.removeAllViews();
            for (Reminder reminder : reminders) {
                View tableRow = getTableRow(reminder);
                binding.reminderTable.addView(tableRow);
            }
        });

        binding.backBtn.setOnClickListener(v->{
            getParentFragmentManager().popBackStack();
        });
    }

    //    public void refresh() {
//        reminderTable.removeAllViews();
//        if (reminderList == null || reminderList.isEmpty()) return;
//
//        for (Reminder reminder : reminderList) {
//            View tableRow = LayoutInflater.from(requireContext()).inflate(R.layout.reminder_item, null);
//            ImageView ivPoster = tableRow.findViewById(R.id.movie_poster);
//            TextView line1 = tableRow.findViewById(R.id.line_1);
//            TextView line2 = tableRow.findViewById(R.id.line_2);
//            ImageView ivDelete = tableRow.findViewById(R.id.iv_delete);
//
//            Picasso.get()
//                    .load(MyConstants.IMAGE_BASE_URL + reminder.getMoviePoster())
//                    .into(ivPoster);
//
//            String line1Text = reminder.getMovieTitle() + " - " +
//                    reminder.getMovieReleaseDate().substring(0, 4) + " - " +
//                    new DecimalFormat("0.0").format(reminder.getMovieRating()) + "/10";
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTimeInMillis(reminder.getTimestamp());
//            String line2Text = calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DATE) +
//                    " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
//            line1.setText(line1Text);
//            line2.setText(line2Text);
//
//
//            ivDelete.setOnClickListener(v -> {
//                try (ReminderDBHelper dbHelper = new ReminderDBHelper(requireContext())) {
//                    // Xoá khỏi DB
//                    Log.d("qz", "Reminder deleted from DB: " + reminder.getId());
//                    dbHelper.deleteReminder(reminder.getId());
//
//                    // Cancel Pending intent
//                    Intent intent = new Intent(requireContext(), ReminderReceiver.class);
//                    PendingIntent pendingIntent = PendingIntent.getBroadcast(requireContext(), reminder.getId(), intent, PendingIntent.FLAG_IMMUTABLE);
//
//                    if (pendingIntent != null) {
//                        pendingIntent.cancel();
//                        AlarmManager alarmManager = (AlarmManager) requireContext().getSystemService(Context.ALARM_SERVICE);
//                        if (alarmManager != null) {
//                            alarmManager.cancel(pendingIntent);
//                        }
//                    }
//
//                    // Xoá khỏi danh sách
//                    reminderList.remove(reminder);
//                    refresh(); // Gọi refresh để cập nhật UI
//
//                    //Thông báo cho Activity để refresh
//                    Intent broadcastIntent = new Intent("com.example.ojtbadamockproject.REFRESH_REMINDERS");
//                    broadcastIntent.putExtra("reminder_id", reminder.getId());
//                    LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(broadcastIntent);
//
//                    //TOAST
//                    if (MyConstants.SHOW_ACTION_TOAST) {
//                        Toast.makeText(requireContext(), "Reminder deleted", Toast.LENGTH_SHORT).show();
//                    }
//
//                } catch (Exception e) {
//                    Log.e("qz_error", e.toString());
//                }
//            });
//
//            tableRow.setOnClickListener(v -> {
//                Bundle bundle = new Bundle();
//                bundle.putString("movie_poster_path", reminder.getMoviePoster());
//                getParentFragmentManager().setFragmentResult("reminder_selected", bundle);
//            });
//
//
//            reminderTable.addView(tableRow);
//        }
//    }

    private View getTableRow(Reminder reminder){
        View tableRow = LayoutInflater.from(requireContext()).inflate(R.layout.reminder_item, null);

        ImageView ivPoster = tableRow.findViewById(R.id.movie_poster);
        TextView line1 = tableRow.findViewById(R.id.line_1);
        TextView line2 = tableRow.findViewById(R.id.line_2);
        ImageView ivDelete = tableRow.findViewById(R.id.iv_delete);

        Picasso.get()
                .load(MyConstants.IMAGE_BASE_URL + reminder.getMoviePoster())
                .into(ivPoster);

        String line1Text = reminder.getMovieTitle() + " - " +
                reminder.getMovieReleaseDate().substring(0, 4) + " - " +
                new DecimalFormat("0.0").format(reminder.getMovieRating()) + "/10";

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(reminder.getTimestamp());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String line2Text = year + "/" + month + "/" + date + " " + hour + ":" + minute;

        line1.setText(line1Text);
        line2.setText(line2Text);

        ivDelete.setOnClickListener(v->{
            reminderViewModel.deleteReminder(reminder);
            //Cancel Pending intent
            WorkManager.getInstance(requireContext()).cancelAllWorkByTag(String.valueOf(reminder.getId()));
        });

        return tableRow;
    }
}