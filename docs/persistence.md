# Data persistence (ArtConn EA)

## What is stored
All data is stored on the phone in a Room database file called `artconn.db`. Nothing is hard-coded: every artwork, comment and tip is created by the user.

## How it is structured (four tables)
| Table | Columns |
|---|---|
| `artists` | id, name, phone |
| `artworks` | id, artistId, title, description, medium, eventName, imagePath, createdAt |
| `comments` | id, artworkId, authorName, text, rating, createdAt |
| `tips` | id, artistId, artworkId, artworkTitle, amountKes, payerPhone, mpesaRef, createdAt |

An artwork belongs to an artist. A comment belongs to an artwork. Deleting an artwork also deletes its comments (cascade). Tips have no link on purpose, so earnings stay in history after an artwork is deleted.

## How data is inserted
A screen calls its ViewModel, the ViewModel calls a repository, and the repository calls a DAO function marked `@Insert`, for example `ArtworkDao.insert`. Room writes a new row and returns its id.

## How data is retrieved
DAO read functions use `@Query` and return a `Flow`. A Flow sends a fresh list every time the table changes, so the screen updates by itself.

## How data is updated
The ViewModel copies the existing object with new values (`artwork.copy(title = ...)`) and calls `ArtworkDao.update`. The id stays the same, so the same row is changed.

## How data is deleted
The ViewModel calls `ArtworkDao.delete` (or `CommentDao.delete`) after the user confirms in a dialog.

## Proof it persists
Add data, swipe the app away from the recent apps screen, open it again: everything is still there.
